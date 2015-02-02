package com.godwan.code;


public class FieldInfo {
	
	/** 类型定义*/
	private String classType;
	/** 泛型名称*/
	private String genericName;
	/** 定义名称*/
	private String fieldName;
	
	public String getClassType() {
		return classType;
	}
	
	public void setClassType(String classType) {
		this.classType = classType;
	}
	
	public String getGenericName() {
		return genericName;
	}
	
	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	@Override
	public String toString() {
		return String.format("类型:%s, 泛型：%s, 字段名%s", classType, genericName, fieldName);
	}
	
}
