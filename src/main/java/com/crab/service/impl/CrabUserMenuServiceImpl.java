package com.crab.service.impl;

import com.crab.common.exception.BusinessException;
import com.crab.domain.CrabMenu;
import com.crab.domain.CrabUserMenu;
import com.crab.mapper.CrabUserMenuMapper;
import com.crab.model.bo.UserMsgBO;
import com.crab.service.CrabUserMenuService;
import com.crab.utils.PublicUtils;
import com.crab.utils.ThreadLocalMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.crab.constants.AuthConstant.USER_MSG_KEY;

@Slf4j
@Service
public class CrabUserMenuServiceImpl extends BaseService<CrabUserMenu> implements CrabUserMenuService{

    @Resource
    private CrabUserMenuMapper crabUserMenuMapper;

    @Override
    public List<CrabMenu> queryUserMenuList() throws BusinessException {
        List<CrabMenu> menuList;
        Object o = ThreadLocalMap.get(USER_MSG_KEY);
        if (PublicUtils.isNull(o)) {
            menuList = new ArrayList<>();
        } else {
            UserMsgBO userMsgBO = (UserMsgBO) o;
            log.info("queryUserMenuList userMsgBO ==> {}", userMsgBO);
            menuList = crabUserMenuMapper.queryUserMenuList(userMsgBO);
        }
        if (PublicUtils.isNull(menuList)) {
            log.error("查询菜单为空, 进入游客模式!");
        }
        log.info("queryUserMenuList result==> {}", menuList);
        return menuList;
    }
}
