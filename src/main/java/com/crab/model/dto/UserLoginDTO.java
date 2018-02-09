package com.crab.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Data
public class UserLoginDTO implements Serializable{


    private String loginName;

    private String loginPwd;

    @NotEmpty(message = "验证码不能为空!")
    private String accessCode;




}
