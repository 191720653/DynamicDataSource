package com.spring.boot.demo.DynamicDataSource.service;

import com.spring.boot.demo.DynamicDataSource.bean.Test;

public interface ITestService {
	
	public int insert(Test test);
	
	public Test select(Long id);
	
	public int updateByPrimaryKey(Test test);
	
	public int delete(Long id);

}
