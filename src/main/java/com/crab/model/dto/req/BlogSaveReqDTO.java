package com.crab.model.dto.req;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
public class BlogSaveReqDTO implements Serializable{

    private static final long serialVersionUID = -6127966034409463398L;

    private String id;

    /**
     * 文章标题
     */
    @NotBlank(message = "文章标题不能空!")
    private String title;

    /**
     * 文章内容
     */
    @NotBlank(message = "文章内容不能空!")
    private String content;

}
