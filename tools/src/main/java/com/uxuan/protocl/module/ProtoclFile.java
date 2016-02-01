package com.uxuan.protocl.module;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 协议文件
 *
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月28日 下午9:10:06
 */
public final class ProtoclFile {

	/** 包名*/
	private String pkg;
	
	/** 导入的包 */
	private final Set<String> imps;
	
	/** 消息文件名称*/
	private final String name;

	/** 消息文件包含的消息 */
	private final List<ProtoclMsg> messages;
	
	public ProtoclFile(String name) {
		this.name = name;
		this.imps = new HashSet<String>();
		this.messages = new ArrayList<ProtoclMsg>();
	}

	/**
	 * @return the packageName
	 */
	public String getPkg() {
		return pkg;
	}

	/**
	 * @param pkg the packageName to set
	 */
	public void setPkg(String pkg) {
		if (this.pkg != null) {
			throw new RuntimeException("file only one package, old:" + this.pkg + ", given:" + pkg);
		}
		
		this.pkg = pkg;
	}

	/**
	 * @return the imports
	 */
	public Set<String> getImps() {
		return imps;
	}
	
	/**
	 * 添加导入包
	 * 
	 * @param importStr
	 */
	public void addImport(String importStr) {
		this.imps.add(importStr);
	}

	/**
	 * @return the fileName
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the messages
	 */
	public List<ProtoclMsg> getMessages() {
		return messages;
	}

	/**
	 * 添加导入包
	 * 
	 * @param importStr
	 */
	public void addMessage(ProtoclMsg msg) {
		this.messages.add(msg);
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		
		if (pkg != null) {
			buf.append("package ").append(pkg).append(";");
		}
		
		buf.append("\n\n");
		for (String imp : imps) {
			buf.append("import ").append(imp).append(";");
		}
		
		
		for (ProtoclMsg msg : messages){
			buf.append("\n\n");
			buf.append(msg);
		}
		
		return buf.toString();
	}
}
