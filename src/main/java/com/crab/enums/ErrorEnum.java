package com.crab.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 错误码
 * Created by lyh on 2017/12/29.
 */
public enum ErrorEnum {

    ;
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    ErrorEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static List<Map<Object, Object>> getList() {
        List<Map<Object, Object>> result = new ArrayList<>();
        for (ErrorEnum errorEnum : ErrorEnum.values()) {
            Map<Object, Object> item = new HashMap<>();
            item.put(errorEnum.getCode(), errorEnum.getName());
            result.add(item);
        }
        return result;
    }
}
