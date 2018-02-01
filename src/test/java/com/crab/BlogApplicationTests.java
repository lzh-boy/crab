package com.crab;

import com.crab.domain.CrabUser;
import com.crab.mapper.CrabUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BlogApplicationTests {

	@Resource
	private CrabUserMapper crabUserMapper;
	@Test
	public void contextLoads() {
		List<CrabUser> crabUsers = crabUserMapper.queryUserByDTO();
		log.info(crabUsers.get(0).getAccountNoStatus().getStatusCode());
	}

}
