package com.crab.service.impl;

import com.crab.common.exception.BusinessException;
import com.crab.common.utils.PublicUtils;
import com.crab.domain.CrabUser;
import com.crab.model.vo.UserLoginVO;
import com.crab.service.TokenService;
import com.crab.utils.JacksonUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import static com.crab.constants.CommonConstant.TOKEN_KEY;

/**
 * @scope注解
 * singleton		:单例的.(默认)
 * prototype		:多例的.-->每次getBean都会得到不同的实例
 * request		:应用在web工程中的,创建一个Bean的实例,将Bean的实例存入到request的域中.
 * session		:应用在web工程中的,创建一个Bean的实例,将Bean的实例存入到session的域中.
 * globalsession	:应用在web工程中的,集群环境.如果没有集群环境的话,配置globalsession与session一致.
 * @author lyh
 */
@Service(value = "tokenService")
@Scope(value = "singleton")
public class TokenServiceImpl implements TokenService{

    public final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);


    @PostConstruct
    public void init() {
        System.out.println("初始化");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁");
    }

    /*
    * @Transactional
    * value: 指定要使用的事务管理器
    * propagation 指定事务传播行为
    * isolation 指定事务隔离行为
    * */
    @Transactional
    public void update() {
        System.out.println("+1");
    }

    @Resource
    private StringRedisTemplate rt;

    @Value("120")
    private Integer expiredMinutes;


    @Override
    public String getSecretKey() throws BusinessException {
        Integer keyLength = 16;
        String complexCode = PublicUtils.createComplexCode(keyLength);
        if (StringUtils.isEmpty(complexCode)) {
            logger.error("获取complexCoce失败!");
            throw new BusinessException("获取用户加密秘钥失败!");
        }
        return complexCode;
    }

    @Override
    public String encodeToken(UserLoginVO crabUser) throws Exception {
        String crabUserMsg = JacksonUtil.toJson(crabUser);
        String tokenKey = this.getTokenKey();
        // 生成Token的时间
        DateTime now = DateTime.now();
        // token失效时间
        DateTime deadTime = now.plusMinutes(expiredMinutes);
        String token;
        try {
            token = Jwts.builder().setSubject(crabUser.getUserName()).claim("crabUserMsg", crabUserMsg)
                    .setIssuedAt(now.toDate()).setExpiration(deadTime.toDate()).signWith(SignatureAlgorithm.ES256, tokenKey).compact();
        } catch (Exception ex) {
            logger.error("生成token失败 == > {}", ex);
            throw ex;
        }
        return token;
    }

    private String getTokenKey() {
        String tokenKey;
        Object tokenKeyFromRedis = this.getRedisValue(TOKEN_KEY);
        if (null == tokenKeyFromRedis) {
            tokenKey = PublicUtils.UUID();
        }
        tokenKey = (String) tokenKeyFromRedis;
        return tokenKey;
    }


    private Object getRedisValue(String key) {
        ValueOperations<String, String> stringStringValueOperations = rt.opsForValue();
        return stringStringValueOperations.get(key);
    }

    @Override
    public String decodeToken(CrabUser crabUser) {

        return null;
    }

}
