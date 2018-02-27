package com.crab.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * mvc跨域配置
 * @author lyh
 */
@Configuration
@Slf4j
public class CrossDomainConfig {

    @Resource
    private RestConfig restConfig;

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                log.info("---------------进入跨域配置");
                // /page/** 后面的/**一定要加!
                registry.addMapping("/page/**")
                        .allowedOrigins(restConfig.getHomeUrl());
                log.info("---------------跨域配置完成");
            }
        };
    }

}
