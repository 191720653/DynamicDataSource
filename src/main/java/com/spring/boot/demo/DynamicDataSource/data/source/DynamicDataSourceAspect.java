package com.spring.boot.demo.DynamicDataSource.data.source;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.spring.boot.demo.DynamicDataSource.data.source.annotation.DataSourceAnnotation;

/**
 * 数据源注解切面实现
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

	@Before("@annotation(dataSourceAnnotation)")
	public void before(JoinPoint point, DataSourceAnnotation dataSourceAnnotation) {
		Class<?> clazz = point.getTarget().getClass();
		MethodSignature signature = (MethodSignature) point.getSignature();
		try {
			Method method = clazz.getMethod(signature.getName(), signature.getParameterTypes());
			if (method.isAnnotationPresent(DataSourceAnnotation.class)) {
				// 根据注解设置数据源
				DataSourceAnnotation annotation = method.getAnnotation(DataSourceAnnotation.class);
				DynamicDataSourceContextHolder.setDataSource(annotation.value());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After("@annotation(dataSourceAnnotation)")
	public void after(JoinPoint point, DataSourceAnnotation dataSourceAnnotation) {
		DynamicDataSourceContextHolder.setDefaultDataSource();
	}

}
