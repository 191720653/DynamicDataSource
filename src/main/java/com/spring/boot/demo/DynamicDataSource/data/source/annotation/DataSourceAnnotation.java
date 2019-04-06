package com.spring.boot.demo.DynamicDataSource.data.source.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.spring.boot.demo.DynamicDataSource.data.source.enums.DataSourceEnum;

/**
 * 自定义数据源注解
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceAnnotation {
	
	DataSourceEnum value() default DataSourceEnum.DATA_SOURCE_MASTER;
	
}
