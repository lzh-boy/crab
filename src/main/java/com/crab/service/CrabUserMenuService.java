package com.crab.service;

import com.crab.common.exception.BusinessException;
import com.crab.domain.CrabMenu;
import com.crab.domain.CrabUserMenu;

import java.util.List;

public interface CrabUserMenuService extends IService<CrabUserMenu>{

    List<CrabMenu> queryUserMenuList() throws BusinessException;

}
