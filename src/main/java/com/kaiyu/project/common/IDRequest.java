package com.kaiyu.project.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求
 *
 * @author kaiyu
 */
@Data
public class IDRequest implements Serializable {

    /**
     * id
     */
    private Long id;
    private static final long serialVersionUID = 1L;
}