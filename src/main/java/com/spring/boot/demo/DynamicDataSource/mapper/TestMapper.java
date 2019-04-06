package com.spring.boot.demo.DynamicDataSource.mapper;

import com.spring.boot.demo.DynamicDataSource.bean.Test;

public interface TestMapper {

    int insert(Test record);

    Test selectByPrimaryKey(Long id);
    
    int updateByPrimaryKey(Test record);
    
    int delete(Long id);

}