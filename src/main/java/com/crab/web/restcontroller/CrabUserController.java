package com.crab.web.restcontroller;

import com.crab.common.exception.BusinessException;
import com.crab.common.model.vo.wrap.WrapMapper;
import com.crab.common.model.vo.wrap.Wrapper;
import com.crab.domain.CrabMenu;
import com.crab.model.bo.UserMsgBO;
import com.crab.model.dto.req.UserLoginDTO;
import com.crab.model.vo.UserLoginVO;
import com.crab.service.CrabUserMenuService;
import com.crab.service.CrabUserService;
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
    public Wrapper<UserLoginVO> loginIn(@RequestBody @Valid UserLoginDTO userLoginDTO, BindingResult bindingResult) {
        logger.info("用户登录 ==> {}", userLoginDTO);
        logger.info("用户登录 bindingResult ==> {}", bindingResult);
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
     * 用户登出
     * @return
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public Wrapper loginOut(HttpServletRequest request) {
        logger.info("用户登出 ==>");
        Wrapper result;
        try {
            UserMsgBO userMsgByToken = getUserMsgByToken();
            crabUserService.userLoginout(request, userMsgByToken);
            result = WrapMapper.ok();
        } catch (BusinessException ex) {
            logger.error("用户登出出错 ==> {}", ex);
            result = new Wrapper(Wrapper.ERROR_CODE, ex.getMessage());
        } catch (Exception ex) {
            logger.error("用户登出出错 ==> {}", ex);
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
