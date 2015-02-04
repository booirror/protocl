package org.itas.tool.netbuf;

import org.itas.tool.netbuf.MsgFiledType.FieldType;


public class MsgField {
	
	
	/**
	 * 消息属性类型
	 */
	private FieldType defType;

	/**
	 * 如果消息是vector  定义vector泛型类型
	 */
	private FieldType genericType;

	/**
	 * 消息类型
	 */
	private String messageType;
	
	/**
	 * 消息属性名称
	 */
	private String defName;
	
	public MsgField(Finder finder) {
		this.defType = FieldType.parse(finder.group(1));
		if (defType == FieldType.MESSAGE) {
			messageType = finder.group(1);
		} else if (defType == FieldType.VECTOR) {
			messageType = finder.group(3);
		}
		this.genericType = FieldType.parse(finder.group(3));;
		this.defName = finder.group(4);
	}
	
	public FieldType getDefType() {
		return defType;
	}
	
	public String getMessageType() {
		return messageType;
	}

	public FieldType getGenericType() {
		return genericType;
	}

	public String getDefName() {
		return defName;
	}

	@Override
	public String toString() {
		return String.format("%s%s %s;", defType.def(), getGenericTypeStr(), defName);
	}
	
	private String getGenericTypeStr() {
		if (genericType == null) {
			return "";
		}
		
		return String.format("<%s>", genericType.def());
	}
}
