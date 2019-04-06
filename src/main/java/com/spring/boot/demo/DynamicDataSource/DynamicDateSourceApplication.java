package com.spring.boot.demo.DynamicDataSource;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.spring.boot.demo.DynamicDataSource.bean.Test;
import com.spring.boot.demo.DynamicDataSource.service.ITestService;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class }) // 求掉Spring的数据源自动配置
public class DynamicDateSourceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DynamicDateSourceApplication.class, args);
	}
	
	@Autowired
	private ITestService iTestService;
	
	@PostConstruct
	public void initTest() {
		// init data
		Test insert = new Test();
		insert.setName("insert");
		// insert
		iTestService.insert(insert);
		Long id = insert.getId();
		System.out.println(id);
		// select
		Test select = iTestService.select(id);
		System.out.println(select.toString());
		// update
		select.setName("update");
		int update = iTestService.updateByPrimaryKey(select);
		System.out.println(update);
		// delete
		int delete = iTestService.delete(id);
		System.out.println(delete);
	}
	
}
