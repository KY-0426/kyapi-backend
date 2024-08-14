package com.kaiyu.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaiyu.kyapicommon.model.entity.InterfaceInfo;
import com.kaiyu.project.common.ErrorCode;
import com.kaiyu.project.exception.BusinessException;
import com.kaiyu.project.mapper.InterfaceInfoMapper;
import com.kaiyu.project.service.InterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2024-01-17 20:08:54
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{
    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {

     // 判断接口类型是否为空，为空则抛出参数错误的异常
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 获取接口对象的名称
        String name = interfaceInfo.getName();


        // 创建时，所有参数必须非空

        // 如果是添加操作
        if (add) {
            if (StringUtils.isAnyBlank() ) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        // 如果接口名称不为空 且  长度大于 五十  返回  名称过长
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
    }
}




