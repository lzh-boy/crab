package com.crab.model.dto.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询博客详情DTO
 */
@Data
public class QueryBlogReqDTO implements Serializable{

    private static final long serialVersionUID = -8112641497021427055L;

    /**
     * 文章序列号
     */
    private String serialNo;
}
