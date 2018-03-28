package com.crab.service.impl;

import com.crab.common.exception.BusinessException;
import com.crab.domain.CrabMsgboard;
import com.crab.mapper.CrabMsgboardMapper;
import com.crab.model.bo.UserMsgBO;
import com.crab.model.dto.req.MsgBoradDTO;
import com.crab.model.dto.req.QueryMsgBoradDTO;
import com.crab.service.CrabMsgboardService;
import com.crab.utils.IpUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveContent(MsgBoradDTO msgBoradDTO, UserMsgBO userMsgByToken, HttpServletRequest request) throws BusinessException {
        CrabMsgboard crabMsgboard = new CrabMsgboard();
        crabMsgboard.setContent(msgBoradDTO.getContent());
        crabMsgboard.setUserNickName(msgBoradDTO.getUserNickName());
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

    /**
     * 查询留言列表
     * @param queryMsgBoradDTO
     * @return
     */
    @Override
    public List<CrabMsgboard> queryContentList(QueryMsgBoradDTO queryMsgBoradDTO) {
        logger.info("查询留言列表 ==> {}", queryMsgBoradDTO);
        return crabMsgboardMapper.queryContentList(queryMsgBoradDTO);
    }
}
