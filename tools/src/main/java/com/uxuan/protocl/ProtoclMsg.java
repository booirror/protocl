package com.uxuan.protocl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月28日 下午9:10:26
 */
public class ProtoclMsg {

	/** message name */
	private final String name;
	
	/** 是否枚举类型*/
	private final boolean isEnum;
	
	/** message attributes*/
	private final List<ProtoclAttr> attributes;
	
	public ProtoclMsg(String name, boolean isEnum) {
		this.name = name;
		this.isEnum = isEnum;
		this.attributes = new ArrayList<ProtoclAttr>();;
	}

	public boolean isEnum() {
		return isEnum;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the attributes
	 */
	public List<ProtoclAttr> getAttributes() {
		return attributes;
	}
	
	/**
	 * 添加
	 * 
	 * @param attribute
	 */
	public void addAttribute(ProtoclAttr attribute) {
		this.attributes.add(attribute);
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		if (isEnum) {
			buf.append("enum ").append(name);
		} else {
			buf.append("message ").append(name);
		}
		
		buf.append(" {");
		for (ProtoclAttr attr : attributes) {
			buf.append("\n\t").append(attr);
		}
		
		buf.append("\n");
		buf.append("}");
		
		return buf.toString();
	}
	
}
