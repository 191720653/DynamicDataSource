package com.spring.boot.demo.DynamicDataSource.data.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.spring.boot.demo.DynamicDataSource.data.source.enums.DataSourceEnum;

/**
 * 自定义动态数据源类
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	private final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

	@Override
	protected Object determineCurrentLookupKey() {
		DataSourceEnum dataSourceEnum = DynamicDataSourceContextHolder.getDataSource();
		logger.info("当前使用数据源为：{}", dataSourceEnum);
		return dataSourceEnum;
	}

}