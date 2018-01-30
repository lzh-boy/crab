package com.crab.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lyh
 * 2018年1月25日14:15:56
 */
@Data
public class UserLoginVO implements Serializable{

    private static final long serialVersionUID = -6493343700767717076L;

    private String userId;
    private String userName;
    private String serialNo;
    private String token;
    private String nickName;
}
