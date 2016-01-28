package com.uxuan.buffer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.uxuan.buffer.util.Next;

/**
 * 定义的消息文件
 * 
 * @author liuzhen(liuxing521a@gmail.com)
 * @crateTime 2015年2月3日上午11:02:06
 */
public final class MsgFile implements Next {
	
	/** 包名*/
	private String packageName;
	
	/** 导入的包 */
	private Set<String> imports;
	
	/** 消息文件名称*/
	private String fileName;

	/** 消息文件包含的消息 */
	private List<MsgBody> bodys;
	
	public MsgFile(File file) {
		this.fileName = file.getName().split("\\.")[0];
		this.bodys = new ArrayList<>();
		this.imports = new HashSet<>();
	}

	public List<MsgBody> getMsgBodys() {
		return bodys;
	}

	public void addMsgBody(MsgBody classInfo) {
		this.bodys.add(classInfo);
	}
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Set<String> getImports() {
		return imports;
	}

	public void addImport(String name) {
		this.imports.add(name);
	}

	/**
	 * 是否有vector类型
	 * 
	 * @return true:有；false:没有
	 */
	public boolean isArrayExist() {
		return bodys.stream().filter(o->o.isArrayExist()).findFirst().isPresent();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("package ").append(packageName).append(";");

		builder.append("\n");
		for (String impt : imports) {
			builder.append("\n").append("import ").append(impt).append(";");
		}
		
		builder.append("\n");
		for (MsgBody body : bodys) {
			builder.append("\n\n").append(body);
		}
		
		return builder.toString();
	}
}