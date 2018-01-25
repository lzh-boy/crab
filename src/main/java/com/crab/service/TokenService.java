package com.crab.service;

import com.crab.common.exception.BusinessException;
import com.crab.domain.CrabUser;
import com.crab.model.vo.UserLoginVO;

public interface TokenService {
    String getSecretKey() throws BusinessException;

    String encodeToken(UserLoginVO crabUser) throws Exception;

    String getTokenKey();

    String decodeToken(CrabUser crabUser);
}
