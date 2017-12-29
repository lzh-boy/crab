package com.crab.enums;

/**
 * 用户认证状态
 * Created by lyh on 2017/12/29.
 */
public enum  IdentificationStatusEnum {

    IDENTIFIED_PASS("1", "认证通过"),
    IDENTIFIED_DENY("0", "认证不通过");

    private String statusCode;
    private String statusName;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    IdentificationStatusEnum(String statusCode, String statusName) {
        this.statusCode = statusCode;
        this.statusName = statusName;
    }
}
