package com.spring.boot.demo.DynamicDataSource.bean;

/**
 * 测试类
 */
public class Test {
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	@Override
	public String toString() {
		return "Test [id=" + id + ", name=" + name + "]";
	}
}