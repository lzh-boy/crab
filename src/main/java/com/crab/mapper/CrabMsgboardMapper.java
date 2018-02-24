package com.crab.mapper;

import com.crab.domain.CrabMsgboard;
import com.crab.model.dto.QueryMsgBoradDTO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CrabMsgboardMapper extends Mapper<CrabMsgboard> {
    List<CrabMsgboard> queryContentList(QueryMsgBoradDTO queryMsgBoradDTO);
}