package com.crab.web.restcontroller;

import com.crab.common.exception.BusinessException;
import com.crab.common.model.vo.wrap.WrapMapper;
import com.crab.common.model.vo.wrap.Wrapper;
import com.crab.domain.CrabMenu;
import com.crab.model.bo.UserMsgBO;
import com.crab.model.dto.UserLoginDTO;
import com.crab.model.vo.UserLoginVO;
import com.crab.service.CrabUserMenuService;
import com.crab.service.CrabUserService;
import com.crab.utils.CookieUtil;
import com.crab.utils.PublicUtils;
import com.crab.utils.ThreadLocalMap;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.crab.constants.AuthConstant.USER_MSG_KEY;

/**
 * Created by lyh on 2017/12/29.
 */
@RestController
@RequestMapping(value = "/page/crab/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CrabUserController extends BaseController{

    @Resource
    private CrabUserService crabUserService;
    @Resource
    private CrabUserMenuService crabUserMenuService;


    /**
     * 用户登录
     * 拦截器不拦
     * @param userLoginDTO
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Wrapper<UserLoginVO> loginIn(@RequestBody UserLoginDTO userLoginDTO) {
        logger.info("用户登录 ==> {}", userLoginDTO);
        Wrapper<UserLoginVO> result;
        try {
            UserLoginVO userLoginVO = crabUserService.userLogin(userLoginDTO);
            result = WrapMapper.success(userLoginVO);
        } catch (BusinessException ex) {
            logger.error("用户登录出错 ==> {}", ex);
            result = new Wrapper<>(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            logger.error("用户登录出错 ==> {}", ex);
            result = WrapMapper.error();
        }
        return result;
    }

    /**
     * 用户菜单查询
     * @return
     */
    @RequestMapping(value = "/menuList", method = RequestMethod.POST)
    public Wrapper<List<CrabMenu>> menuList() {
        logger.info("用户菜单查询 ==>");
        Wrapper<List<CrabMenu>> result;
        try {
            List<CrabMenu> menuList = crabUserMenuService.queryUserMenuList();
            result = WrapMapper.success(menuList);
        } catch (BusinessException ex) {
            logger.error("用户菜单查询出错 ==> {}", ex);
            result = new Wrapper<>(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            logger.error("用户菜单查询出错 ==> {}", ex);
            result = WrapMapper.error();
        }
        return result;
    }
}
