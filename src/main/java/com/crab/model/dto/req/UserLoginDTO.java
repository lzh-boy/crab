package com.crab.model.dto.req;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Data
public class UserLoginDTO implements Serializable{


    private static final long serialVersionUID = 1926080359850849059L;

    private String loginName;

    private String loginPwd;

    private String accessCode;




}
