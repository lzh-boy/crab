package com.crab.config;

import com.crab.web.interceptor.VueViewInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    @Resource
    private VueViewInterceptor vueViewInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(vueViewInterceptor).addPathPatterns("/page/**")
                .excludePathPatterns("/page/crab/user/login"
                        , "/page/crab/token/getPwdSecretKey"
                        , "/page/crab/msgboard/saveContent"
                        , "/page/crab/msgboard/queryContentList"
                        , "/page/crab/blog/queryContentDetail"
                        , "/page/crab/blog/queryContentList");
    }
}
