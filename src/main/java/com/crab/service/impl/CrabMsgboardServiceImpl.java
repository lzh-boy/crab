package com.crab.service.impl;

import com.crab.common.exception.BusinessException;
import com.crab.domain.CrabMsgboard;
import com.crab.mapper.CrabMsgboardMapper;
import com.crab.model.bo.UserMsgBO;
import com.crab.model.dto.MsgBoradDTO;
import com.crab.model.dto.QueryMsgBoradDTO;
import com.crab.service.CrabMsgboardService;
import com.crab.utils.IpUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class CrabMsgboardServiceImpl extends BaseService<CrabMsgboard> implements CrabMsgboardService {

    @Resource
    private CrabMsgboardMapper crabMsgboardMapper;

    /**
     * 保存留言信息
     * @param msgBoradDTO
     * @param userMsgByToken
     * @param request
     * @throws BusinessException
     */
    @Override
    public void saveContent(MsgBoradDTO msgBoradDTO, UserMsgBO userMsgByToken, HttpServletRequest request) throws BusinessException {
        CrabMsgboard crabMsgboard = new CrabMsgboard();
        crabMsgboard.setContent(msgBoradDTO.getContent());
        crabMsgboard.setCreateTime(new Date());
        String ipAddr = IpUtils.getIpAddr(request);
        crabMsgboard.setIpAddr(ipAddr);
//        crabMsgboard.setUserSerialNo(userMsgByToken.getSerialNo());
//        crabMsgboard.setUserNickName(userMsgByToken.getNickName());
        int save = this.save(crabMsgboard);
        if (save < 1) {
            throw new BusinessException("保存留言信息失败");
        }
    }

    @Override
    public List<CrabMsgboard> queryContentList(QueryMsgBoradDTO queryMsgBoradDTO) {
        logger.info("queryContentList ==> {}", queryMsgBoradDTO);
        return crabMsgboardMapper.queryContentList(queryMsgBoradDTO);
    }
}
