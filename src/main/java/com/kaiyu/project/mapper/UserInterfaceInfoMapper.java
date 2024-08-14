package com.kaiyu.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaiyu.kyapicommon.model.entity.UserInterfaceInfo;

import java.util.List;

/**
* @author 21154
* @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Mapper
* @createDate 2024-02-18 23:11:12
* @Entity com.kaiyu.project.model.entity.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {


    /**
     * 查询用户接口信息表中，按照指定的limit 参数进行筛选
     * 返回 limit 条记录的接口信息
     * @param limit
     * @return
     */
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);
}




