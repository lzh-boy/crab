package com.crab.service.impl;

import com.crab.common.exception.BusinessException;
import com.crab.common.utils.CheckUtil;
import com.crab.domain.CrabUser;
import com.crab.mapper.CrabUserMapper;
import com.crab.model.dto.UserLoginDTO;
import com.crab.model.vo.UserLoginVO;
import com.crab.service.CrabUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * Created by lyh on 2017/12/29.
 */
@Service
public class CrabUserServiceImpl extends BaseService<CrabUser> implements CrabUserService {

    @Resource
    private CrabUserMapper crabUserMapper;

    @Override
    public UserLoginVO userLogin(UserLoginDTO userLoginDTO) throws BusinessException {
        logger.info("userLogin ==> {}", userLoginDTO);
        if (null == userLoginDTO || CheckUtil.isNull(userLoginDTO.getLoginPwd())
                || CheckUtil.isNull(userLoginDTO.getLoginName())) {
            logger.error("用户登录出错! 参数有误!");
            throw new BusinessException("参数有误!");
        }
        CrabUser crabUser = new CrabUser();
        BeanUtils.copyProperties(userLoginDTO, crabUser);
        List<CrabUser> crabUsers = crabUserMapper.select(crabUser);
        if (CheckUtil.isNull(crabUsers)) {
            logger.error("用户登录出错! 账号或密码有误!");
            throw new BusinessException("登录失败! 账号或密码有误!");
        }
        return null;
    }
}
