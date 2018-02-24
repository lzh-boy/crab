package com.crab.utils;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Page<T> {
    /** 页面大小*/
    @NotNull(message = "页面大小不可空")
    private Integer pageSize;
    /** 页码*/
    @NotNull(message = "页码不可空")
    private Integer pageNum;
    /** 查询参数*/
    private T param;
}
