package com.crab.web.interceptor;

import com.crab.common.exception.BusinessException;
import com.crab.model.bo.UserMsgBO;
import com.crab.service.CrabUserService;
import com.crab.utils.PublicUtils;
import com.crab.utils.ThreadLocalMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.crab.constants.AuthConstant.USER_MSG_KEY;

/**
 * 权限拦截器
 * Created by lyh on 2017/12/28.
 */
@Service
@Slf4j
public class VueViewInterceptor implements HandlerInterceptor{


    @Resource
    private CrabUserService crabUserService;

    /**
     * controller执行前调用此方法
     * return false表示中止执行, return true表示继续执行
     * 这里可以做登录校验, 权限拦截等
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("进入Vue拦截器");
        log.info("进入Vue拦截器");
        String authHeader = httpServletRequest.getHeader("Authorization");
        try {
            if (PublicUtils.isNull(authHeader)) {
                log.error("用户请求无证书");
            }
            if (!PublicUtils.isNull(authHeader)) {
                String token = authHeader.substring(7);
                log.info("token ==> {}", token);
                if (PublicUtils.isNull(token)) {
                    log.error("token信息为空!");
                    throw new BusinessException("登录出错!");
                }
                UserMsgBO userMsgBO = crabUserService.getUserMsgByToken(token);
                if (PublicUtils.isNull(userMsgBO)) {
                    log.error("登录过期或无法该token解析出有效信息");
                    throw new BusinessException("登录信息无效!");
                }
                ThreadLocalMap.put(USER_MSG_KEY, userMsgBO);
            }
            log.info("进入Vue拦截器 authHeader===> {}", authHeader);
        } catch (Exception ex) {
            log.error("用户登录出错 ===> {}", ex);
            this.handleException(httpServletRequest, httpServletResponse, ex);
            return false;
        }
        log.info("验权成功");
        return true;
    }

    private void handleException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Exception ex) {
        if (ex instanceof BusinessException) {

        } else {

        }
    }

    /**
     * controller执行后但未返回视图前调用此方法
     * 这里可以在返回视图前对数据模型进行加工处理
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * controller执行后且视图返回后调用此方法
     * 这里可以做操作日志, 资源清理
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
