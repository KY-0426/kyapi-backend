package com.kaiyu.kyapicommon.service;



import com.kaiyu.kyapicommon.model.entity.InterfaceInfo;

/**
* @author Administrator
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-01-17 20:08:54
*/
public interface InnerInterfaceInfoService {
    /**
     * 从数据库中查询模拟接口是否存在（请求路径、请求方法、请求参数）
     */
    InterfaceInfo getInterfaceInfo(String path, String method);

}
