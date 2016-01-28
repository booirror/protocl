package com.uxuan.protocl;

import java.util.List;

/**
 *
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月28日 下午9:10:26
 */
public class ProtoclMsg {

	/** message name */
	private String name;
	
	/** message attributes*/
	private List<ProtoclAttr> attributes;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the attributes
	 */
	public List<ProtoclAttr> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(List<ProtoclAttr> attributes) {
		this.attributes = attributes;
	}
	
	
}
