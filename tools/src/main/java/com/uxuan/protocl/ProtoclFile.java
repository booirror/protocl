package com.uxuan.protocl;

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
	private String packageName;
	
	/** 导入的包 */
	private final Set<String> imports;
	
	/** 消息文件名称*/
	private final String fileName;

	/** 消息文件包含的消息 */
	private final List<ProtoclMsg> messages;
	
	public ProtoclFile(String fileName) {
		this.fileName = fileName;
		this.imports = new HashSet<String>();
		this.messages = new ArrayList<ProtoclMsg>();
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		if (this.packageName != null) {
			throw new RuntimeException("file only one package, old:" + this.packageName + ", given:" + packageName);
		}
		
		this.packageName = packageName;
	}

	/**
	 * @return the imports
	 */
	public Set<String> getImports() {
		return imports;
	}
	
	/**
	 * 添加导入包
	 * 
	 * @param importStr
	 */
	public void addImport(String importStr) {
		this.imports.add(importStr);
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
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
		
		if (packageName != null) {
			buf.append("package ").append(packageName).append(";");
		}
		
		buf.append("\n\n");
		for (String imp : imports) {
			buf.append("import ").append(imp).append(";");
		}
		
		
		for (ProtoclMsg msg : messages){
			buf.append("\n\n");
			buf.append(msg);
		}
		
		return buf.toString();
	}
}
