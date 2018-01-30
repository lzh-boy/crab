package com.crab.model.bo;

import lombok.Data;

@Data
public class UserMsgBO {
    private String userId;
    private String userName;
    private String serialNo;
    private String token;
    private String nickName;
}
