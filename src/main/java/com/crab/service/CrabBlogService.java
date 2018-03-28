package com.crab.service;

import com.crab.common.exception.BusinessException;
import com.crab.domain.CrabBlog;
import com.crab.model.bo.UserMsgBO;
import com.crab.model.dto.req.BlogSaveReqDTO;
import com.crab.model.dto.req.QueryBlogLIstReqDTO;
import com.crab.model.dto.req.QueryBlogReqDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CrabBlogService extends IService<CrabBlog> {

    /**
     * 保存博客
     * @param blogSaveReqDTO
     * @param userMsgByToken
     * @param request
     */
    void saveContent(BlogSaveReqDTO blogSaveReqDTO, UserMsgBO userMsgByToken, HttpServletRequest request) throws BusinessException;


    /**
     * 查询文章列表
     * @param param
     * @return
     */
    List<CrabBlog> queryContentList(QueryBlogLIstReqDTO param) throws BusinessException;

    /**
     * 查询文章详情
     * @param reqDTO
     * @return
     */
    CrabBlog queryContentDetail(QueryBlogReqDTO reqDTO) throws BusinessException;
}
