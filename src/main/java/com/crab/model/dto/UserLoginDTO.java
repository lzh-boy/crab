package com.crab.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDTO implements Serializable{

    private String loginName;

    private String loginPwd;


}
