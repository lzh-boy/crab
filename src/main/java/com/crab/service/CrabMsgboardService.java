package com.crab.service;

import com.crab.common.exception.BusinessException;
import com.crab.domain.CrabMsgboard;
import com.crab.model.bo.UserMsgBO;
import com.crab.model.dto.MsgBoradDTO;
import com.crab.model.dto.QueryMsgBoradDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CrabMsgboardService extends IService<CrabMsgboard>{
    void saveContent(MsgBoradDTO msgBoradDTO, UserMsgBO userMsgByToken, HttpServletRequest request) throws BusinessException;

    List<CrabMsgboard> queryContentList(QueryMsgBoradDTO queryMsgBoradDTO);
}
