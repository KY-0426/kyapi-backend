package com.kaiyu.project.service;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.kaiyu.project.common.BaseResponse;
import com.kaiyu.project.model.dto.WxUserInfo;
import com.kaiyu.project.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;


/**
 * @author 成大事
 * @since 2022/7/27 22:47
 */
public interface WxUserInfoService {

    /**
     * 登录
     * @param code code
     * @return   WxMaJscode2SessionResult
     */
    UserVO wxLogin(String code, HttpServletRequest request);

    /**
     * 获取用户信息
     * @param userInfo  包含一些加密的信息
     * @return  WxMaUserInfo
     */
    BaseResponse<WxMaUserInfo> getUserInfo(WxUserInfo userInfo);
}

