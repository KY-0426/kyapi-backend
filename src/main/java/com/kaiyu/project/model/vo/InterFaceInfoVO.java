package com.kaiyu.project.model.vo;

import com.kaiyu.kyapicommon.model.entity.InterfaceInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 接口信息封装视图
 * @author kaiyu
 * @TableName product
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InterFaceInfoVO extends InterfaceInfo {

    /**
     * 调用次数
     */
    private Integer totalNum;

    private static final long serialVersionUID = 1L;
}