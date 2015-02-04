package org.itas.buffer.tools.service.java;

import org.itas.buffer.tools.MsgFiledType;

public class JavaFiledType implements MsgFiledType {

	/**
	 * java基础类型
	 */
	private String basicType;
	
	/**
	 * java 包装类型
	 */
	private String wrappType;
	
	
	public JavaFiledType(String basicType, String wrappType) {
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
}
