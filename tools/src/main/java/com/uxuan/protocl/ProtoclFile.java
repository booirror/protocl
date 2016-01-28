package com.uxuan.protocl;

import java.util.List;
import java.util.Set;

/**
 * 协议文件
 *
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月28日 下午9:10:06
 */
public class ProtoclFile {

	/** 包名*/
	private String packageName;
	
	/** 导入的包 */
	private Set<String> imports;
	
	/** 消息文件名称*/
	private String fileName;

	/** 消息文件包含的消息 */
	private List<ProtoclMsg> messages;

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
		this.packageName = packageName;
	}

	/**
	 * @return the imports
	 */
	public Set<String> getImports() {
		return imports;
	}

	/**
	 * @param imports the imports to set
	 */
	public void setImports(Set<String> imports) {
		this.imports = imports;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the messages
	 */
	public List<ProtoclMsg> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(List<ProtoclMsg> messages) {
		this.messages = messages;
	}
	
}
