package com.crab.domain;

import com.crab.enums.AccountNoStatusEnum;
import com.crab.enums.AccountNoStatusEnumT;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tbl_crab_user")
public class CrabUser {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 登录名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 登录密码
     */
    @Column(name = "login_pwd")
    private String loginPwd;

    /**
     * 用户昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 流水号
     */
    @Column(name = "serial_no")
    private String serialNo;

    /**
     * 会员账号
     */
    @Column(name = "member_no")
    private String memberNo;

    /**
     * 用户认证状态: 1通过认证 0未通过认证
     */
    @Column(name = "identification_status")
    private String identificationStatus;

    /**
     * 注册时间
     */
    @Column(name = "registration_time")
    private Date registrationTime;

    /**
     * 用户来源
     */
    @Column(name = "user_source")
    private String userSource;

    /**
     * 账号状态: 0 正常 1 停用
     */
    @Column(name = "account_no_status")
    private AccountNoStatusEnumT accountNoStatus;

    /**
     * 连续输错密码次数
     */
    @Column(name = "pwd_error_count")
    private Short pwdErrorCount;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人id
     */
    @Column(name = "creator_id")
    private Integer creatorId;

    @Column(name = "last_operator")
    private String lastOperator;

    @Column(name = "last_operator_id")
    private String lastOperatorId;

    @Column(name = "last_operator_time")
    private Date lastOperatorTime;

    /**
     * 最后登录IP
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 是否删除   0 未删除  1 删除
     */
    private Short yn;

}