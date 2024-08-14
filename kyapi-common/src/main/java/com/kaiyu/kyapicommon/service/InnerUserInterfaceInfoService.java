package com.kaiyu.kyapicommon.service;

import com.kaiyu.kyapicommon.model.entity.InterfaceInfo;
import com.kaiyu.kyapicommon.model.entity.UserInterfaceInfo;


/**
* @author 21154
* @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Service
* @createDate 2024-02-18 23:11:12
*/
public interface InnerUserInterfaceInfoService {


    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);

    /***
     * 统计接口剩余次数
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    int liftCount(long interfaceInfoId, long userId);

}

