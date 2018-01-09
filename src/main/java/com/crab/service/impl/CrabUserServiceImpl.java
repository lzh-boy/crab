package com.crab.service.impl;

import com.crab.common.exception.BusinessException;
import com.crab.common.utils.PublicUtils;
import com.crab.domain.CrabUser;
import com.crab.mapper.CrabUserMapper;
import com.crab.model.dto.UserLoginDTO;
import com.crab.model.vo.UserLoginVO;
import com.crab.service.CrabUserService;
import com.crab.service.TokenService;
import com.crab.utils.HttpAesUtil;
import com.crab.utils.ThreadLocalMap;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.crab.constants.CommonConstant.PWD_SECRET_KEY;
import static com.crab.constants.CommonConstant.TOKEN_KEY;

/**
 *
 * Created by lyh on 2017/12/29.
 */
@Service
public class CrabUserServiceImpl extends BaseService<CrabUser> implements CrabUserService {

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
        Object redisValue = getRedisValue(PWD_SECRET_KEY);
        if (redisValue == null ) {
            logger.error("用户的pwd_secret_key在redis里过期了");
            throw new BusinessException("用户登录过期");
        }
        String pwdSecretKey = (String) redisValue;
        // 对用户名和密码进行解密
        String decryptName = HttpAesUtil.decrypt(loginName, pwdSecretKey, false, pwdSecretKey);
        String decryptPwd = HttpAesUtil.decrypt(loginPwd, pwdSecretKey, false, pwdSecretKey);
        CrabUser crabUser = new CrabUser();
        crabUser.setLoginName(decryptName);
        crabUser.setLoginPwd(decryptPwd);
        List<CrabUser> crabUsers = crabUserMapper.select(crabUser);
        if (PublicUtils.isNull(crabUsers)) {
            logger.error("用户登录出错! 账号或密码有误!");
            throw new BusinessException("登录失败! 账号或密码有误!");
        }
        CrabUser crabUserFinded = crabUsers.get(0);
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setUserId(crabUserFinded.getId());
        userLoginVO.setSerialNo(crabUserFinded.getSerialNo());
        userLoginVO.setUserName(crabUserFinded.getLoginName());
        userLoginVO.setNickName(crabUserFinded.getNickName());
        String token =  tokenService.encodeToken(userLoginVO);
        // 将TOKEN加入本地缓存
        ThreadLocalMap.put(TOKEN_KEY, token);
        userLoginVO.setToken(token);
        return userLoginVO;
    }

}
