package com.crab.enums;

/**
 * 账号状态
 * Created by lyh on 2017/12/29.
 */
public enum AccountNoStatusEnumT {

    ACCOUNT_NO_SHUT_OFF(1, "停用"),
    ACCOUNT_NO_NORMAL(0, "正常");

    private Integer statusCode;
    private String statusName;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    AccountNoStatusEnumT(Integer statusCode, String statusName) {
        this.statusCode = statusCode;
        this.statusName = statusName;
    }
}
