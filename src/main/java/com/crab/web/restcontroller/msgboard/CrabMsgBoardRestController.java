package com.crab.web.restcontroller.msgboard;

import com.crab.common.model.vo.wrap.WrapMapper;
import com.crab.common.model.vo.wrap.Wrapper;
import com.crab.common.utils.WrapperUtil;
import com.crab.domain.CrabMsgboard;
import com.crab.model.dto.MsgBoradDTO;
import com.crab.model.dto.QueryMsgBoradDTO;
import com.crab.service.CrabMsgboardService;
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

/**
 * 留言板
 * @author 罗迎豪
 * @date 2018年2月18日21:22:31
 */
@Slf4j
@RestController
@RequestMapping(value = "/page/crab/msgboard", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CrabMsgBoardRestController extends BaseController{

    @Resource
    private CrabMsgboardService crabMsgboardService;

    /**
     * 保存留言
     * @param msgBoradDTO
     * @return
     */
    @RequestMapping(value = "saveContent", method = RequestMethod.POST)
    public Wrapper saveContent(@RequestBody @Valid MsgBoradDTO msgBoradDTO, BindingResult bindingResult, HttpServletRequest request) {
        log.info("saveContent ==> {}", msgBoradDTO);
        try {
            handleBindingResult(bindingResult);
//            UserMsgBO userMsgByToken = getUserMsgByToken();
            crabMsgboardService.saveContent(msgBoradDTO, null, request);
        } catch (Exception ex) {
            return WrapperUtil.handleException(ex);
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MSG);
    }

    /**
     * 查询留言板列表
     * @param page
     * @return
     */
    @RequestMapping(value = "queryContentList", method = RequestMethod.POST)
    public Wrapper<PageInfo<CrabMsgboard>> queryContentList(@RequestBody @Valid Page<QueryMsgBoradDTO> page, BindingResult bindingResult) {
        log.info("queryContentList ==> {}", page);
        try {
            handleBindingResult(bindingResult);
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<CrabMsgboard> contentList = crabMsgboardService.queryContentList(page.getParam());
            PageInfo<CrabMsgboard> result = new PageInfo<>(contentList);
            return WrapperUtil.success(result);
        } catch (Exception ex) {
            return WrapperUtil.handleException(ex);
        }
    }
}
