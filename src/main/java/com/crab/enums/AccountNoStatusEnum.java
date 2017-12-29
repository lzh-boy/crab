package com.crab.enums;

/**
 * 账号状态
 * Created by lyh on 2017/12/29.
 */
public enum AccountNoStatusEnum {

    ACCOUNT_NO_SHUT_OFF("1", "停用"),
    ACCOUNT_NO_NORMAL("0", "正常");

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

    AccountNoStatusEnum(String statusCode, String statusName) {
        this.statusCode = statusCode;
        this.statusName = statusName;
    }
}
