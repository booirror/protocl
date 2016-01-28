package com.uxuan.protocl;

/**
 *
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月28日 下午9:10:46
 */
public class ProtoclAttr {

	/** 属性类型*/
	private final DefType defType;
	
	/** 属性类型*/
	private DefType genericType;
	
	/** 属性名称*/
	private final String attrName;
	
	public ProtoclAttr(String attrName) {
		this.defType = null;
		this.attrName = attrName;
	}
	
}
