package com.crab.mapper;

import com.crab.domain.CrabUser;
import com.crab.enums.AccountNoStatusEnum;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CrabUserMapper extends Mapper<CrabUser> {
    @Results(value = {
        @Result(column = "accountNoStatus", property = "accountNoStatus",jdbcType = JdbcType.VARCHAR, javaType = AccountNoStatusEnum.class, typeHandler = EnumTypeHandler.class)
    })
    List<CrabUser> queryUserByDTO();
}