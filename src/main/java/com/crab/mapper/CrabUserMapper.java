package com.crab.mapper;

import com.crab.domain.CrabUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface CrabUserMapper extends Mapper<CrabUser> {

}