package com.crab.web.restcontroller;

import com.crab.common.exception.BusinessException;
import com.crab.model.bo.UserMsgBO;
import com.crab.utils.PublicUtils;
import com.crab.utils.ThreadLocalMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.crab.constants.AuthConstant.USER_MSG_KEY;

/**
 * Created by lyh on 2017/12/29.
 */

public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected UserMsgBO getUserMsgByToken() throws BusinessException {
        Object o = ThreadLocalMap.get(USER_MSG_KEY);
        if (PublicUtils.isNull(o)) {
            throw new BusinessException("token验证失败!");
        }
        return (UserMsgBO)o;
    }

}
