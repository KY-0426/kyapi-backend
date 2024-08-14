package com.kaiyu.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaiyu.kyapicommon.model.entity.UserInterfaceInfo;
import com.kaiyu.project.common.ErrorCode;
import com.kaiyu.project.exception.BusinessException;
import com.kaiyu.project.mapper.UserInterfaceInfoMapper;
import com.kaiyu.project.service.UserInterfaceInfoService;
import org.springframework.stereotype.Service;

/**
* @author 21154
* @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Service实现
* @createDate 2024-02-18 23:11:12
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService {

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        // 判断接口类型是否为空，为空则抛出参数错误的异常
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 创建时，所有参数必须非空

        // 如果是添加操作
        if (add) {
            if (userInterfaceInfo.getInterfaceInfoId() <= 0 || userInterfaceInfo.getUserId() <= 0 ){
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口或用户不存在");
            }
        }
        // 如果接口名称不为空 且  长度大于 五十  返回  名称过长
        if (userInterfaceInfo.getLeftNum()  < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能小于 0 ");
        }
    }



    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        // 1. 校验数据合法性
        if (interfaceInfoId <= 0 || userId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不存在");
        }
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("interfaceInfoId",interfaceInfoId);
        updateWrapper.eq("userId",userId);
        // 剩余次数要大于等于0
        // TODO 伙伴系统有  考虑加锁
        updateWrapper.gt("leftNum", 0);
        updateWrapper.setSql("leftNum  = leftNum - 1, totalNum = totalNum + 1");

        return this.update(updateWrapper);
    }

}




