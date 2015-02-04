package org.itas.tool.netbuf.service.java;

import org.itas.tool.netbuf.MsgFiledType;

public class JavaFiledType implements MsgFiledType {

	/**
	 * java基础类型
	 */
	private String basicType;
	
	/**
	 * java 包装类型
	 */
	private String wrappType;
	
	/**
	 * 定义类型
	 */
	private FieldType filedType;
	
	public JavaFiledType(FieldType filedType, String basicType, String wrappType) {
		this.filedType = filedType;
		this.basicType = basicType;
		this.wrappType = wrappType;
	}
	
	@Override
	public String basicType() {
		return basicType;
	}

	@Override
	public String wrappType() {
		return wrappType;
	}

	@Override
	public FieldType defType() {
		return filedType;
	}

}
