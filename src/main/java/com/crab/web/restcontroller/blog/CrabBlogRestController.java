package com.crab.web.restcontroller.blog;

import com.crab.common.model.vo.wrap.WrapMapper;
import com.crab.common.model.vo.wrap.Wrapper;
import com.crab.common.utils.WrapperUtil;
import com.crab.domain.CrabBlog;
import com.crab.model.bo.UserMsgBO;
import com.crab.model.dto.req.BlogSaveReqDTO;
import com.crab.model.dto.req.QueryBlogLIstReqDTO;
import com.crab.model.dto.req.QueryBlogReqDTO;
import com.crab.service.CrabBlogService;
import com.crab.utils.Page;
import com.crab.web.restcontroller.BaseController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/page/crab/blog", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CrabBlogRestController extends BaseController{

    @Resource
    private CrabBlogService crabBlogService;


    /**
     * 保存文章
     * @param blogSaveReqDTO
     * @return
     */
    @RequestMapping(value = "saveContent", method = RequestMethod.POST)
    public Wrapper saveContent(@RequestBody @Valid BlogSaveReqDTO blogSaveReqDTO, BindingResult bindingResult, HttpServletRequest request) {
        log.info("保存文章 ==> {}", blogSaveReqDTO);
        try {
            handleBindingResult(bindingResult);
            UserMsgBO userMsgByToken = getUserMsgByToken();
            crabBlogService.saveContent(blogSaveReqDTO, userMsgByToken, request);
        } catch (Exception ex) {
            return WrapperUtil.handleException(ex);
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MSG);
    }

    /**
     * 更新文章
     * @param blogSaveReqDTO
     * @return
     */
    @RequestMapping(value = "updateContent", method = RequestMethod.POST)
    public Wrapper updateContent(@RequestBody @Valid BlogSaveReqDTO blogSaveReqDTO, BindingResult bindingResult, HttpServletRequest request) {
        log.info("更新文章 ==> {}", blogSaveReqDTO);
        try {
            handleBindingResult(bindingResult);
            UserMsgBO userMsgByToken = getUserMsgByToken();
            crabBlogService.updateContent(blogSaveReqDTO, userMsgByToken, request);
        } catch (Exception ex) {
            return WrapperUtil.handleException(ex);
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MSG);
    }

    /**
     * 查询文章列表
     * @param page
     * @return
     */
    @RequestMapping(value = "queryContentList", method = RequestMethod.POST)
    public Wrapper<PageInfo<CrabBlog>> queryContentList(@RequestBody @Valid Page<QueryBlogLIstReqDTO> page, BindingResult bindingResult) {
        log.info("查询文章列表 ==> {}", page);
        try {
            handleBindingResult(bindingResult);
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<CrabBlog> contentList = crabBlogService.queryContentList(page.getParam());
            PageInfo<CrabBlog> result = new PageInfo<>(contentList);
            return WrapperUtil.success(result);
        } catch (Exception ex) {
            return WrapperUtil.handleException(ex);
        }
    }

    /**
     * 查询文章详情
     * @param reqDTO
     * @return
     */
    @RequestMapping(value = "queryContentDetail", method = RequestMethod.POST)
    public Wrapper<CrabBlog> queryContentDetail(@RequestBody @Valid QueryBlogReqDTO reqDTO, BindingResult bindingResult) {
        log.info("查询文章详情 ==> {}", reqDTO);
        try {
            handleBindingResult(bindingResult);
            CrabBlog crabBlog = crabBlogService.queryContentDetail(reqDTO);
            return WrapperUtil.success(crabBlog);
        } catch (Exception ex) {
            return WrapperUtil.handleException(ex);
        }
    }

}
