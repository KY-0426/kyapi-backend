package com.kaiyu.project.model.dto.interfaceInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口调用请求
 *
 * @TableName interfaceInfo
 */
@Data
public class InterfaceInfoInvokeRequest implements Serializable {

    /**
     * 主键
     */
    //@TableId(type = IdType.AUTO)
    private Long id;



    /**
     * 用户请求参数
     */
    private String userRequestParams;




}