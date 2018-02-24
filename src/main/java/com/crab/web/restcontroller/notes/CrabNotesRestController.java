package com.crab.web.restcontroller.notes;

import com.crab.common.model.vo.wrap.WrapMapper;
import com.crab.common.model.vo.wrap.Wrapper;
import com.crab.web.restcontroller.BaseController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 生活随笔
 * @author lyh
 * @date 2018年1月15日18:26:00
 */
@RestController
@RequestMapping(value = "/page/crab", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CrabNotesRestController extends BaseController{

    @RequestMapping(value = "getNotesIndex", method = RequestMethod.POST)
    public Wrapper getNotesIndex() {
        logger.info("CrabNotesRestController ==> getNotesIndex");
        List<String> result = new ArrayList<>();
        result.add("a");
        result.add("b");
        result.add("c");
        result.add("d");
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MSG, result);
    }
}
