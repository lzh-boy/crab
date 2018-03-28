package com.crab.mapper;

import com.crab.domain.CrabBlog;
import com.crab.model.dto.req.QueryBlogLIstReqDTO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CrabBlogMapper extends Mapper<CrabBlog> {

    /**
     * 查询博客列表
     * @param param
     * @return
     */
    List<CrabBlog> selectByParam(QueryBlogLIstReqDTO param);
}