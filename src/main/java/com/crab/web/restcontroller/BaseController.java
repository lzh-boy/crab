package com.crab.web.restcontroller;

import com.crab.common.exception.BusinessException;
import com.crab.model.bo.UserMsgBO;
import com.crab.utils.PublicUtils;
import com.crab.utils.ThreadLocalMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

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

    /**
     * Hibernate Validator校验结果处理
     * @author <a href="lyhluo@163.com"/>罗迎豪</a>
     * @date
     */
    protected void handleBindingResult(BindingResult bindingResult) throws BusinessException {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        if (allErrors.isEmpty()) {
            return;
        }
        String defaultMessage = allErrors.get(0).getDefaultMessage();
        throw new BusinessException(defaultMessage);
    }

}
