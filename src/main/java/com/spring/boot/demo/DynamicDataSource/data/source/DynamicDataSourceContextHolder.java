package com.spring.boot.demo.DynamicDataSource.data.source;

import com.spring.boot.demo.DynamicDataSource.data.source.enums.DataSourceEnum;

public class DynamicDataSourceContextHolder {

	// 定义一个ThreadLocal变量，保存数据源类型（保证线程安全，多个线程之间互不影响）
	private static final ThreadLocal<DataSourceEnum> DATA_SOURCE_CONTEXT_HOLDER = new ThreadLocal<>();

	public static final DataSourceEnum DEFAULT_DATA_SOURCE = DataSourceEnum.DATA_SOURCE_MASTER;
	
	static {
		setDefaultDataSource(); // 默认指定主库
	}
	
	public static void setDefaultDataSource() {
		DATA_SOURCE_CONTEXT_HOLDER.set(DEFAULT_DATA_SOURCE);
	}

	public static void setDataSource(DataSourceEnum dataSourceEnum) {
		DATA_SOURCE_CONTEXT_HOLDER.set(dataSourceEnum);
	}

	public static DataSourceEnum getDataSource() {
		return DATA_SOURCE_CONTEXT_HOLDER.get();
	}

}