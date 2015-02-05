package org.itas.buffer.tools;

import static org.itas.buffer.tools.util.StringUtils.firstCharUpCase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 定义的消息文件
 * @author liuzhen(liuxing521a@gmail.com)
 * @crateTime 2015年2月3日上午11:02:06
 */
public class MsgFile {
	
	/**
	 * 包名
	 */
	private String packageName;
	
	/**
	 * 导入的包
	 */
	private Set<String> imports;
	
	/**
	 * 消息文件名称
	 */
	private String fileName;
	
	/**
	 * 消息文件自动分配的序号,short前2个字节
	 */
	private byte fileOrder;
	

	/**
	 * 消息文件包含的消息
	 */
	private List<MsgBody> bodys;
	
	public MsgFile(File file, byte order) {
		this.fileName = file.getName().split("\\.")[0];
		this.fileOrder = order;
		this.bodys = new ArrayList<>();
		this.imports = new HashSet<>();
	}

	public String getMsgFileName(boolean isFirstUpCase) {
		return isFirstUpCase ? firstCharUpCase(fileName) : fileName;
	}
	
	public List<MsgBody> getMsgBodys() {
		return bodys;
	}

	public void addMsgBody(MsgBody classInfo) {
		this.bodys.add(classInfo);
	}
	
	public byte getFileOrder() {
		return fileOrder;
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
	 * 获取文件自动分配的16进制序号
	 * @return
	 */
	public String getFileHexOrder() {
		String calzz = Integer.toHexString(fileOrder);
		if (calzz.length() < 2) {
			calzz = "0" + calzz;
		} 
		
		return "0x" + calzz;
	}
	
	/**
	 * 是否有vector类型
	 * @return true:有；false:没有
	 */
	public boolean isVectorExist() {
		for (MsgBody info : bodys) {
			if (info.hasArray()) {
				return true;
			}
		}
		
		return false;
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