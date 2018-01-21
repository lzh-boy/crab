package com.crab.web.restcontroller;

import com.crab.common.model.vo.wrap.WrapMapper;
import com.crab.common.model.vo.wrap.Wrapper;
import com.crab.common.utils.PublicUtils;
import com.crab.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.crab.constants.CommonConstant.PWD_SECRET_KEY;


@RestController
@RequestMapping(value = "/page/crab/token", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CrabTokenController extends BaseController{

    // @Autowired默认是按类型注入, 配合Qualifier实现按名称注入
    @Autowired
    @Qualifier(value = "tokenService")
    private TokenService tokenService;
    // @Resource是按名称注入
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 获取用户密码加密秘钥,
     * 缓存在Redis中,有效期设置为1H
     */
    @RequestMapping(value = "getPwdSecretKey", method = RequestMethod.POST)
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
