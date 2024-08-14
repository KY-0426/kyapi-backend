package com.kaiyu.project.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaiyu.kyapicommon.model.entity.User;
import com.kaiyu.project.common.BaseResponse;
import com.kaiyu.project.common.ErrorCode;
import com.kaiyu.project.common.ResultUtils;
import com.kaiyu.project.exception.BusinessException;
import com.kaiyu.project.mapper.UserMapper;
import com.kaiyu.project.model.dto.WxUserInfo;
import com.kaiyu.project.model.vo.UserVO;
import com.kaiyu.project.service.WxUserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.kaiyu.project.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author kaiyu
 * @since 2022/7/27 22:48
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WxUserInfoServiceImpl extends ServiceImpl<UserMapper, User> implements WxUserInfoService {

    private final WxMaService wxMaService;


    private static final String SALT = "yupi";

    @Resource
    private UserMapper userMapper;

    /**
     * 登录
     * @param code code
     * @return   WxMaJscode2SessionResult
     */
    @Override
    public UserVO wxLogin(String code, HttpServletRequest request) {
        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            String openID =session.getOpenid();
            log.info(session.getSessionKey());
            log.info("openID:"+openID);
            log.info(session.getUnionid());

            // 查询用户是否存在
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("openId", openID);
            User user = userMapper.selectOne(queryWrapper);
            // openid用户不存在  注册用户
            if (user == null) {
                user = new User();

                String userAccount = "微信用户" +openID.substring(0,8);
                String  accessKey =  DigestUtil.md5Hex( SALT +  userAccount + RandomUtil.randomNumbers(5));
                String  secretKey =  DigestUtil.md5Hex( SALT +  userAccount + RandomUtil.randomNumbers(8));
                String defaultUserAvatar = "https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg";

                user.setOpenId(openID);
                user.setUserAccount(userAccount);
                user.setUserAvatar(defaultUserAvatar);
                user.setUserPassword("b0dd3697a192885d7c055db46155b26a");
                user.setAccessKey(accessKey);
                user.setSecretKey(secretKey);

                boolean saveResult = this.save(user);
                if (!saveResult) {
                    throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
                }

            }
            // 3. 记录用户的登录态
            request.getSession().setAttribute(USER_LOGIN_STATE, user);
            log.info(request.getRequestedSessionId());
            log.info(request.getSession().getId());
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);


            return userVO;
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            throw new BusinessException(ErrorCode.OPERATION_ERROR,e.toString());
        } finally {
            WxMaConfigHolder.remove();//清理ThreadLocal
        }
    }

    /**
     * 作废
     * @param userInfo  包含一些加密的信息
     * @return
     */
    @Override
    public BaseResponse<WxMaUserInfo> getUserInfo(WxUserInfo userInfo) {

        // 用户信息校验
        if (!wxMaService.getUserService().checkUserInfo(userInfo.getSessionKey(), userInfo.getRawData(), userInfo.getSignature())) {
            WxMaConfigHolder.remove();//清理ThreadLocal
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }

        // 解密用户信息
        WxMaUserInfo wxMaUserInfo = wxMaService.getUserService().getUserInfo(userInfo.getSessionKey(), userInfo.getEncryptedData(), userInfo.getIv());
        WxMaConfigHolder.remove();//清理ThreadLocal
        return ResultUtils.success(wxMaUserInfo);
    }




}
