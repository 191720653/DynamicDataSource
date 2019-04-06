package com.spring.boot.demo.DynamicDataSource.data.source.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.spring.boot.demo.DynamicDataSource.data.source.DynamicDataSource;
import com.spring.boot.demo.DynamicDataSource.data.source.enums.DataSourceEnum;

/**
 * 数据源相关配置
 */
@Configuration
@MapperScan(basePackages = "com.spring.boot.demo.DynamicDataSource.mapper")
public class DataSourceConfig {

	@Bean(name = "dataSourceMaster")
	@Primary // 当有多个实现类型时，优先使用
	public DataSource dataSourceMaster(MasterConfig masterConfig) throws Exception {
		DruidDataSource druidDataSourceMaster = new DruidDataSource();
		druidDataSourceMaster.setUrl(masterConfig.getUrl());
		druidDataSourceMaster.setUsername(masterConfig.getUsername());
		druidDataSourceMaster.setPassword(masterConfig.getPassword());
		druidDataSourceMaster.setDriverClassName(masterConfig.getDriverClassName());
		return druidDataSourceMaster;
	}

	@Bean(name = "dataSourceSlave")
	public DataSource dataSourceSlave(SlaveConfig slaveConfig) throws Exception {
		DruidDataSource druidDataSourceSalve = new DruidDataSource();
		druidDataSourceSalve.setUrl(slaveConfig.getUrl());
		druidDataSourceSalve.setUsername(slaveConfig.getUsername());
		druidDataSourceSalve.setPassword(slaveConfig.getPassword());
		druidDataSourceSalve.setDriverClassName(slaveConfig.getDriverClassName());
		return druidDataSourceSalve;
	}

	/**
	 * 动态数据源，配置需要使用到的多个数据源
	 * @param master
	 * @param slave
	 * @return
	 */
	@Bean
	public DynamicDataSource dynamicDataSource(@Qualifier("dataSourceMaster") DataSource master, @Qualifier("dataSourceSlave") DataSource slave) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DataSourceEnum.DATA_SOURCE_MASTER, master);
		targetDataSources.put(DataSourceEnum.DATA_SOURCE_SLAVE, slave);
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		dynamicDataSource.setDefaultTargetDataSource(master);
		dynamicDataSource.setTargetDataSources(targetDataSources);
		return dynamicDataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(DynamicDataSource dynamicDataSource) {
		return new DataSourceTransactionManager(dynamicDataSource);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DynamicDataSource dynamicDataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dynamicDataSource);
		sessionFactory.setMapperLocations(((ResourcePatternResolver) new PathMatchingResourcePatternResolver())
				.getResources("classpath*:/mapping/*Mapper.xml"));
		return sessionFactory.getObject();
	}
}