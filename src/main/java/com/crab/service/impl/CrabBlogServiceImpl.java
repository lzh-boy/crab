package com.crab.service.impl;

import com.crab.common.exception.BusinessException;
import com.crab.common.model.vo.wrap.Wrapper;
import com.crab.domain.CrabBlog;
import com.crab.mapper.CrabBlogMapper;
import com.crab.model.bo.UserMsgBO;
import com.crab.model.dto.req.BlogSaveReqDTO;
import com.crab.model.dto.req.QueryBlogLIstReqDTO;
import com.crab.model.dto.req.QueryBlogReqDTO;
import com.crab.service.CrabBlogService;
import com.crab.utils.PublicUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 博客业务类
 */
@Slf4j
@Service
public class CrabBlogServiceImpl extends BaseService<CrabBlog> implements CrabBlogService{

    @Resource
    private CrabBlogMapper crabBlogMapper;

    /**
     * 保存博客
     * @param blogSaveReqDTO
     * @param userMsgByToken
     * @param request
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveContent(BlogSaveReqDTO blogSaveReqDTO, UserMsgBO userMsgByToken, HttpServletRequest request) throws BusinessException {
        log.info("保存博客业务类 blogSaveReqDTO ==> {}", blogSaveReqDTO);
        log.info("保存博客业务类 userMsgByToken ==> {}", userMsgByToken);
        if (null == blogSaveReqDTO || null == userMsgByToken) {
            throw new BusinessException(Wrapper.UNKOWN_ERROR_MSG);
        }
        CrabBlog crabBlog = new CrabBlog();
        BeanUtils.copyProperties(blogSaveReqDTO, crabBlog);
        Date now = new Date();
        crabBlog.setSerialNo(UUID.randomUUID().toString().replace("-", ""));
        crabBlog.setCreateTime(now);
        crabBlog.setCreateUser(userMsgByToken.getSerialNo());
        super.save(crabBlog);
    }


    /**
     * 查询文章列表
     * @param param
     * @return
     */
    @Override
    public List<CrabBlog> queryContentList(QueryBlogLIstReqDTO param) throws BusinessException {
        log.info("查询文章列表 QueryBlogLIstReqDTO ==> {}", param);
        return crabBlogMapper.selectByParam(param);
    }

    /**
     * 查询文章详情
     * @param reqDTO
     * @return
     */
    @Override
    public CrabBlog queryContentDetail(QueryBlogReqDTO reqDTO) throws BusinessException {
        log.info("查询文章详情 reqDTO ==> {}", reqDTO);
        CrabBlog crabBlog = new CrabBlog();
        crabBlog.setSerialNo(reqDTO.getSerialNo());
        List<CrabBlog> blogs = crabBlogMapper.select(crabBlog);
        if (PublicUtils.isNull(blogs)) {
            throw new BusinessException("没有该内容");
        }
        return blogs.get(0);
    }
}
