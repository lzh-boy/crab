package com.crab.web.restcontroller;

import com.crab.common.model.vo.wrap.WrapMapper;
import com.crab.common.model.vo.wrap.Wrapper;
import com.crab.common.utils.PublicUtils;
import com.crab.service.TokenService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

import static com.crab.constants.CommonConstant.PWD_SECRET_KEY;


@Controller(value = "/page/crab/token")
public class CrabTokenController extends BaseController{

    @Resource
    private TokenService tokenService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 获取用户密码加密秘钥
     */
    @RequestMapping(value = "getSecretKey", method = RequestMethod.POST)
    public Wrapper getPwdSecretKey() {
        Wrapper result;
        try {
            if (!PublicUtils.isNull(this.getSecretKey())) {
                return WrapMapper.ok();
            }
            String pwdSecretKey = tokenService.getSecretKey();
            this.setSecretKey(pwdSecretKey);
            result = WrapMapper.ok();
        } catch (Exception ex) {
            logger.error("用户登录出错 ==> {}", ex);
            result = WrapMapper.error();
        }
        return result;
    }

    private void setSecretKey(String pwdSecretKey) {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set(PWD_SECRET_KEY, pwdSecretKey);
        stringRedisTemplate.expire(PWD_SECRET_KEY, 1L, TimeUnit.HOURS);
    }

    private String getSecretKey() {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        return stringStringValueOperations.get(PWD_SECRET_KEY);
    }
}
