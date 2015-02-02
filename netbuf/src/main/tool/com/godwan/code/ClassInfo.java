package com.godwan.code;

import static com.godwan.code.util.Strings.firstCharUpCase;

import java.util.LinkedList;
import java.util.List;

import com.godwan.code.util.MsgStatus;

public class ClassInfo {
	
	/** 对应结构体名称*/
	private String className;
	/** 命令号头*/
	private FileInfo fileInfo;
	/** 命令号尾*/
	private short xMethodIndex;
	/** 属性列表*/
	private List<FieldInfo> fieldList;
	/** 消息类型*/
	private List<MsgStatus> msgTypeList;
	
	public ClassInfo(FileInfo fileInfo, short xMethodIndex) {
		this.fileInfo = fileInfo;
		this.fieldList = new LinkedList<FieldInfo>();
		this.msgTypeList = new LinkedList<MsgStatus>();
		this.xMethodIndex = xMethodIndex;
	}
	
	public String getClassName(boolean isFirstUpCase) {
		if (isFirstUpCase) {
			return firstCharUpCase(className);
		} else {
			return className;
		}
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setFieldList(List<FieldInfo> fieldList) {
		this.fieldList = fieldList;
	}

	public List<FieldInfo> getFieldList() {
		return fieldList;
	}
	
	public void addField(FieldInfo field) {
		fieldList.add(field);
	}
	
	public boolean contains(MsgStatus msgType) {
		return msgTypeList.contains(msgType);
	}

	public void addMsgType(MsgStatus msgType) {
		this.msgTypeList.add(msgType);
	}
	

	public void addAllMsgType(List<MsgStatus> msgTypeList) {
		this.msgTypeList.addAll(msgTypeList);
	}
	
	public List<MsgStatus> getMessageTypes() {
		return this.msgTypeList;
	}

	public String getHexMessage() {
		return getHexClass() + getHexMethod0();
	}

	public String getHexClass() {
		return "0x" + fileInfo.getHeXClassIndex();
	}
	
	public String getHexMethod() {
		String method = Integer.toHexString(xMethodIndex);
		if (method.length() < 2) {
			method = "0" + method;
		} 
		
		return "0x" + method;
	}
	
	private String getHexMethod0() {
		String method = Integer.toHexString(xMethodIndex);
		if (method.length() < 2) {
			method = "0" + method;
		} 
		
		return method;
	}
	

	public void clear() {
		fieldList.clear();
		className = "";
		msgTypeList.clear();
	}
	
	public boolean hasArray() {
		for (FieldInfo info : fieldList) {
			if (info.getGenericName() != null) {
				return true;
			}
		}
		
		return false;
	}
}
