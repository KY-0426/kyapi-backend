package com.kaiyu.project.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户视图
 *
 * @TableName user
 */
@Data
public class UserAkSkVO implements Serializable {
    /**
     * id
     */
    private Long id;

    private String accessKey;

    private String secretKey;



    private static final long serialVersionUID = 1L;
}