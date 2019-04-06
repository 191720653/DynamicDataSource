package com.spring.boot.demo.DynamicDataSource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.demo.DynamicDataSource.bean.Test;
import com.spring.boot.demo.DynamicDataSource.data.source.annotation.DataSourceAnnotation;
import com.spring.boot.demo.DynamicDataSource.data.source.enums.DataSourceEnum;
import com.spring.boot.demo.DynamicDataSource.mapper.TestMapper;
import com.spring.boot.demo.DynamicDataSource.service.ITestService;

@Service
public class TestServiceImpl implements ITestService {

	@Autowired
	private TestMapper testMapper;
	
	@Override
	@DataSourceAnnotation(DataSourceEnum.DATA_SOURCE_MASTER) // 指定主库
	public int insert(Test test) {
		// TODO Auto-generated method stub
		return testMapper.insert(test);
	}

	@Override
	@DataSourceAnnotation(DataSourceEnum.DATA_SOURCE_SLAVE) // 指定从库
	public Test select(Long id) {
		// TODO Auto-generated method stub
		return testMapper.selectByPrimaryKey(id);
	}

	@Override
	@DataSourceAnnotation // 不指定，默认主库
	public int updateByPrimaryKey(Test test) {
		// TODO Auto-generated method stub
		return testMapper.updateByPrimaryKey(test);
	}

	@Override // 无注解，默认主库
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return testMapper.delete(id);
	}

}
