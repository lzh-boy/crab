package com.crab.service;

import com.crab.common.exception.BusinessException;
import com.crab.domain.CrabUser;
import com.crab.model.dto.UserLoginDTO;
import com.crab.model.vo.UserLoginVO;

/**
 *
 * Created by lyh on 2017/12/29.
 */
public interface CrabUserService extends IService<CrabUser>{

    UserLoginVO userLogin(UserLoginDTO userLoginDTO) throws BusinessException;

}
