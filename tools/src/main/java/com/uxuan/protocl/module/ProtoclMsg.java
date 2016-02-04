package com.uxuan.protocl.module;

import java.util.ArrayList;
import java.util.List;

import com.uxuan.protocl.MessageType;

/**
 *
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月28日 下午9:10:26
 */
public class ProtoclMsg {


	/** message name */
	private final String name;

	private final ProtoclFile file;
	
	/** 是否枚举类型*/
	private final MessageType type;
	
	/** message attributes*/
	private final List<ProtoclAttr> attributes;
	
	public ProtoclMsg(ProtoclFile file, MessageType type, String name) {
		this.type = type;
		this.file = file;
		this.name = name;
		this.attributes = new ArrayList<ProtoclAttr>();;
	}
	
	public ProtoclFile getParent() {
		return file;
	}

	public boolean isType(MessageType type) {
		return type == this.type;
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
		if (isType(MessageType.ENUM)) {
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
