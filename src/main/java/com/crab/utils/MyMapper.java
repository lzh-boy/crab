package com.crab.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //特别注意，该接口不能被扫描到(不能放到mapper下)，否则会出错
}
