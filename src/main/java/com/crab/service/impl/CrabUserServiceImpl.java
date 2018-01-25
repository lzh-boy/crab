package com.crab.service.impl;

import com.crab.common.exception.BusinessException;
import com.crab.domain.CrabUser;
import com.crab.mapper.CrabUserMapper;
import com.crab.model.bo.UserMsgBO;
import com.crab.model.dto.UserLoginDTO;
import com.crab.model.vo.UserLoginVO;
import com.crab.service.CrabUserService;
import com.crab.service.TokenService;
import com.crab.utils.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.crab.constants.AuthConstant.PWD_SECRET_KEY;
import static com.crab.constants.AuthConstant.THREAD_LOCAL_TOKEN;
import static com.crab.constants.AuthConstant.TOKEN_KEY;

/**
 *
 * Created by lyh on 2017/12/29.
 */
@Service
public class CrabUserServiceImpl extends BaseService<CrabUser> implements CrabUserService {


    @Value("30")
    private Integer expiredRemainMinutes;

    @Resource
    private CrabUserMapper crabUserMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private TokenService tokenService;

    @Override
    public UserLoginVO userLogin(UserLoginDTO userLoginDTO) throws Exception {
        logger.info("userLogin ==> {}", userLoginDTO);
        if (null == userLoginDTO) {
            logger.error("用户登录出错! 参数有误!");
            throw new BusinessException("登录失败! 登录信息有误!");
        }
        String loginPwd = userLoginDTO.getLoginPwd();
        String loginName = userLoginDTO.getLoginName();
        if (PublicUtils.isNull(loginPwd)
                || PublicUtils.isNull(loginName)) {
            logger.error("用户登录出错! 参数有误!");
            throw new BusinessException("登录失败! 登录信息有误!");
        }
        // 取用户的登录密码的秘钥PWD_SECRET_KEY = "CRAB_PWD_SECRET_KEY";
        Object redisValue = getRedisValue(PWD_SECRET_KEY);
        if (redisValue == null ) {
            logger.error("用户的pwd_secret_key在redis里过期了, 需要重新登录获取用户登录密码秘钥");
            throw new BusinessException("用户登录过期");
        }
        String pwdSecretKey = (String) redisValue;
        // 对用户名和密码进行解密(会在前端用户登录时使用相同的秘钥对用户名和密码进行加密)
        String decryptName = HttpAesUtil.decrypt(loginName, pwdSecretKey, false, pwdSecretKey);
        // 明文码
        String decryptPwd = HttpAesUtil.decrypt(loginPwd, pwdSecretKey, false, pwdSecretKey);
        decryptPwd = MD5Util.encodeByAES(decryptPwd);
        //去数据库中查询是否有该用户的信息,且校验该用户信息是否正确和有效
        CrabUser crabUser = new CrabUser();
        crabUser.setLoginName(decryptName);
        crabUser.setLoginPwd(decryptPwd);
        List<CrabUser> crabUsers = crabUserMapper.select(crabUser);
        if (PublicUtils.isNull(crabUsers)) {
            logger.error("用户登录出错! 账号或密码有误!");
            throw new BusinessException("登录失败! 账号或密码有误!");
        }
        CrabUser crabUserFinded = crabUsers.get(0);
        this.checkUserValidate(crabUserFinded, crabUser);
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setUserId(crabUserFinded.getId());
        userLoginVO.setSerialNo(crabUserFinded.getSerialNo());
        userLoginVO.setUserName(crabUserFinded.getLoginName());
        userLoginVO.setNickName(crabUserFinded.getNickName());
        // 生成TOKEN存放到本地缓存里
        String token =  tokenService.encodeToken(userLoginVO);
        // 将TOKEN加入本地缓存
        ThreadLocalMap.put(THREAD_LOCAL_TOKEN, token);
        userLoginVO.setToken(token);
        return userLoginVO;
    }



    private void checkUserValidate(CrabUser crabUserFinded, CrabUser crabUser) throws BusinessException {
        //校验用户状态是否有效
        this.checkUserState(crabUserFinded);
    }

    private void checkUserState(CrabUser crabUserFinded) {
        // todo
    }

    @Override
    public UserMsgBO getUserMsgByToken(String token) throws Exception {
        logger.info("getUserMsgByToken ==> {}", token);
        UserMsgBO userMsgBO = new UserMsgBO();
        try {
            if (PublicUtils.isNull(token)) {
                logger.error("getUserMsgByToken ==>token为空");
                throw new BusinessException("登录失败!");
            }
            // 先对token进行解密
            String tokenKey = tokenService.getTokenKey();
            JwtParser jwtParser = Jwts.parser().setSigningKey(tokenKey);
            if (jwtParser == null) {
                logger.error("tokenKey失效, 请重新生成!");
                throw new BusinessException("登录失败!");
            }
            final Claims claims = jwtParser.parseClaimsJws(token).getBody();
            String crabUserMsg = (String)claims.get("crabUserMsg");
            UserLoginVO userLoginVO = JacksonUtil.parseJson(crabUserMsg, UserLoginVO.class);
            Date expiration = claims.getExpiration();
            DateTime now = new DateTime();
            DateTime expirationDate = new DateTime(expiration);
            userMsgBO.setUserLoginVO(userLoginVO);
            // 如果过期时间小于30分钟, 需要续租
            if (now.plusMinutes(expiredRemainMinutes).isAfter(expirationDate)) {
                // todo
            }
        } catch (Exception ex) {
            logger.error("token解密失败 == > {}", ex);
            throw new BusinessException("token解密失败!");
        }
        return userMsgBO;
    }

}
