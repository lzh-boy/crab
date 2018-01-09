package com.crab.service.impl;

import com.crab.service.IService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public abstract class BaseService<T> implements IService<T> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private StringRedisTemplate rt;

	@Autowired
	protected Mapper<T> mapper;

	public Mapper<T> getMapper() {
        return mapper;
    }
	
	@Override
	public List<T> select(T record) {
		return mapper.select(record);
	}

	@Override
	public T selectByKey(Object key) {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<T> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public T selectOne(T record) {
		return mapper.selectOne(record);
	}

	@Override
	public int selectCount(T record) {
		return mapper.selectCount(record);
	}

	@Override
	public List<T> selectByExample(Object example) {
		return mapper.selectByExample(example);
	}

	@Override
	public int save(T record) {
		return mapper.insertSelective(record);
	}

    @Override
    public int batchSave(List<T> list) {
        int result = 0;
        for (T record : list) {
            int count = mapper.insertSelective(record);
            if(count < 1){
                throw new RuntimeException("插入数据失败!");
            }
            result += count;
        }
        return result;
    }

    @Override
	public int update(T entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public int delete(T record) {
		return mapper.delete(record);
	}

	@Override
	public int deleteByKey(Object key) {
		return mapper.deleteByPrimaryKey(key);
	}
	
	@Override
	public int batchDelete(List<T> list) {
		int result = 0;
		for (T record : list) {
			int count = mapper.delete(record);
			if(count < 1){
				throw new RuntimeException("插入数据失败!");
			}
			result += count;
		}
		return result;
	}

	@Override
	public int selectCountByExample(Object example) {
		return mapper.selectCountByExample(example);
	}

	@Override
	public int updateByExample(T record, Object example) {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int deleteByExample(Object example) {
		return mapper.deleteByPrimaryKey(example);
	}

	@Override
	public List<T> selectByRowBounds(T record, RowBounds rowBounds) {
		return mapper.selectByRowBounds(record, rowBounds);
	}

	@Override
	public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
		return mapper.selectByExampleAndRowBounds(example, rowBounds);
	}

	@Override
	public Object getRedisValue(String key) {
		ValueOperations<String, String> stringStringValueOperations = rt.opsForValue();
		return stringStringValueOperations.get(key);
	}

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
