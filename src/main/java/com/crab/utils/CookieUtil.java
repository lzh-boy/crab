package com.crab.utils;

import com.crab.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author lyh
 * @date 2018年1月15日18:53:30
 */
public class CookieUtil {

    static Logger logger = LoggerFactory.getLogger(CookieUtil.class);

    public static void setCookie(String key, String value, String domain, String path, HttpServletResponse resp) throws BusinessException {
        logger.info("setCookie - 设置cookie. key={}, value={}, domain={}. path={}", key, value, domain, path);
        Cookie cookie;
        try {
            cookie = new Cookie(key, URLEncoder.encode(value, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error("Cookie中有乱码, encoding有异常!", e);
            throw new BusinessException("Cookie中有乱码, encoding有异常!");
        }
        cookie.setDomain(domain);
        cookie.setPath(path);
        resp.addCookie(cookie);
    }

    public static void removeCookie(String key, String value, String domain, String path, HttpServletResponse resp) {
        logger.info("clearCookie - 设置cookie. key={},  domain={}. path={}", key, domain, path);
        Cookie cookie = new Cookie(key, null);
        cookie.setPath(path);
        cookie.setDomain(domain);
        // 设置Cookie生命周期为0
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
    }
}
