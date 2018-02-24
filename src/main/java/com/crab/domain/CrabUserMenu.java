package com.crab.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tbl_crab_user_menu")
public class CrabUserMenu {
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