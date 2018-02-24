package com.crab.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
public class MsgBoradDTO implements Serializable {
    private static final long serialVersionUID = -215395350438554703L;
    /**
     * 留言内容
     */
    @NotBlank(message = "留言内容不能空!")
    private String content;
}
