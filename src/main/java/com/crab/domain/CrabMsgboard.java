package com.crab.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tbl_crab_msgboard")
public class CrabMsgboard {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    /**
     * 楼层
     */
    @Column(name = "floor")
    private String floor;

    /**
     * 用户流水号
     */
    @Column(name = "user_serial_no")
    private String userSerialNo;

    /**
     * 用户昵称
     */
    @Column(name = "user_nick_name")
    private String userNickName;

    /**
     * 留言内容
     */
    private String content;

    /**
     * 留言时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 留言者IP地址
     */
    @Column(name = "ip_addr")
    private String ipAddr;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 有效标志位
     */
    private String yn;

}