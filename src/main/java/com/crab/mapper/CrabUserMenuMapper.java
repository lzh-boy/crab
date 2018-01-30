package com.crab.mapper;

import com.crab.domain.CrabMenu;
import com.crab.domain.CrabUserMenu;
import com.crab.model.bo.UserMsgBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CrabUserMenuMapper extends Mapper<CrabUserMenu> {
    List<CrabMenu> queryUserMenuList(UserMsgBO userMsgBO);
}