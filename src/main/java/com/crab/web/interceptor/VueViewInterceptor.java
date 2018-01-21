package com.crab.web.interceptor;

import com.crab.common.utils.PublicUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器
 * Created by lyh on 2017/12/28.
 */
@Service
public class VueViewInterceptor implements HandlerInterceptor{

    private final Logger logger = LoggerFactory.getLogger(VueViewInterceptor.class);

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
        logger.info("进入Vue拦截器");
        logger.info("进入Vue拦截器");
        String authHeader = httpServletRequest.getHeader("Authorization");
        if (PublicUtils.isNull(authHeader)) {
            logger.error("用户请求无证书");
        }
        if (!PublicUtils.isNull(authHeader)) {
            String token = authHeader.substring(7);
            logger.info("token ==> {}", token);
        }
        logger.info("进入Vue拦截器 authHeader===> {}", authHeader);
        return true;
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
