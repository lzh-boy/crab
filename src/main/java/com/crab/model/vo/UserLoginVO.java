package com.crab.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginVO implements Serializable{
    private String userId;
    private String userName;
    private String serialNo;
    private String token;
    private String nickName;

}
