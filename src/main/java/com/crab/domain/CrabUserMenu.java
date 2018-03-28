package com.crab.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "tbl_crab_user_menu")
public class CrabUserMenu implements Serializable{
    private static final long serialVersionUID = -2956491731924776644L;
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    /**
     * 用户流水号
     */
    @Column(name = "user_serial_no")
    private String userSerialNo;

    /**
     * 菜单编码
     */
    @Column(name = "menu_code")
    private String menuCode;

}