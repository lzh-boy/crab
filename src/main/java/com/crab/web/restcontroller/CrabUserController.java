package com.crab.web.restcontroller;

import com.crab.common.exception.BusinessException;
import com.crab.common.model.vo.wrap.WrapMapper;
import com.crab.common.model.vo.wrap.Wrapper;
import com.crab.model.dto.UserLoginDTO;
import com.crab.model.vo.UserLoginVO;
import com.crab.service.CrabUserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by lyh on 2017/12/29.
 */
@Controller
@RequestMapping(value = "/crab/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CrabUserController extends BaseController{

    @Resource
    private CrabUserService crabUserService;


    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Wrapper<UserLoginVO> loginIn(UserLoginDTO userLoginDTO) {
        logger.info("用户登录 ==> {}", userLoginDTO);
        Wrapper<UserLoginVO> result;
        try {
            UserLoginVO userLoginVO = crabUserService.userLogin(userLoginDTO);
            result = new Wrapper<>(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MSG, userLoginVO);
        } catch (BusinessException ex) {
            logger.error("用户登录出错 ==> {}", ex);
            result = new Wrapper<UserLoginVO>(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            logger.error("用户登录出错 ==> {}", ex);
            result = WrapMapper.error();
        }
        return result;
    }
}
