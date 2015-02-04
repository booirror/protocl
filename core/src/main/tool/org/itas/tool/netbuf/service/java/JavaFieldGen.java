package org.itas.tool.netbuf.service.java;


import static org.itas.tool.netbuf.util.StringUtils.firstCharUpCase;
import static org.itas.tool.netbuf.util.StringUtils.nextLine;

import java.util.EnumMap;

import org.itas.tool.netbuf.MsgBody;
import org.itas.tool.netbuf.MsgField;
import org.itas.tool.netbuf.MsgFiledType.FieldType;

public class JavaFieldGen  {
	
	private static final EnumMap<FieldType, JavaFiledType> JAVA_TYPE = new EnumMap<FieldType, JavaFiledType>(FieldType.class) {
		private static final long serialVersionUID = -6861525540105806338L;

		{
			put(FieldType.BOOL, new JavaFiledType(FieldType.BOOL, "boolean", "Boolean"));
			put(FieldType.INT8, new JavaFiledType(FieldType.INT8, "byte", "Byte"));
			put(FieldType.INT16, new JavaFiledType(FieldType.INT16, "short", "Short"));
			put(FieldType.INT, new JavaFiledType(FieldType.INT, "int", "Integer"));
			put(FieldType.INT64, new JavaFiledType(FieldType.INT64, "long", "Long"));
			put(FieldType.FLOAT, new JavaFiledType(FieldType.FLOAT, "float", "Float"));
			put(FieldType.DOUBLE, new JavaFiledType(FieldType.DOUBLE, "double", "Double"));
			put(FieldType.STRING, new JavaFiledType(FieldType.STRING, "String", "String"));
			put(FieldType.VECTOR, new JavaFiledType(FieldType.VECTOR, "List", "List"));
			put(FieldType.MESSAGE, new JavaFiledType(FieldType.MESSAGE, "", ""));
		}
	};
	
	/**
	 * 消息body
	 */
	private MsgBody msgBody;

	/**
	 * 消息field
	 */
	private MsgField msgField;
	
	
	public JavaFieldGen(MsgBody msgBody, MsgField msgField) {
		this.msgBody = msgBody;
		this.msgField = msgField;
	}

	public String defendField() {
		return String.format("private %s %s;", getDefendWithGeneric(), msgField.getDefName());
	}
	
	public String getMethod() {
		StringBuffer methodBuf = new StringBuffer();
		
		methodBuf.append(nextLine(0, 1));
		methodBuf.append(String.format("public %s get%s()", getDefendWithGeneric(), firstCharUpCase(msgField.getDefName())));
		methodBuf.append(" {");
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append(String.format("return this.%s;", msgField.getDefName()));
		
		methodBuf.append(nextLine(1, 1));
		methodBuf.append("}");
		
		return methodBuf.toString();
	}
	
	public String setMethod() {
		StringBuffer methodBuf = new StringBuffer();
		
		methodBuf.append(nextLine(0, 1));
		methodBuf.append(String.format("public %s set%s(%s data) {", 
				msgBody.getMsgName(true), firstCharUpCase(msgField.getDefName()), getDefendWithGeneric()));
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append(String.format("this.%s = data;", msgField.getDefName()));
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append("return this;");
		
		methodBuf.append(nextLine(1, 1));
		methodBuf.append("}");
		
		return methodBuf.toString();
	}
	
	public String setIndexMethod() {
		StringBuffer methodBuf = new StringBuffer();
		
		methodBuf.append(nextLine(0, 1));
		methodBuf.append(String.format("public %s set%s(int index, %s data) {", 
				msgBody.getMsgName(true), firstCharUpCase(msgField.getDefName()), getDefendWithGeneric()));
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append(String.format("this.%s.set(index, data);", msgField.getDefName()));
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append("return this;");
		
		methodBuf.append(nextLine(1, 1));
		methodBuf.append("}");
		
		return methodBuf.toString();
	}
	
	public String addMethod() {
		StringBuffer methodBuf = new StringBuffer();
		
		methodBuf.append(nextLine(0, 1));
		methodBuf.append(String.format("public %s add%s(%s data) {", 
				msgBody.getMsgName(true), firstCharUpCase(msgField.getDefName()), getGenericType()));
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append(String.format("if (%s == null) {", msgField.getDefName()));
		methodBuf.append(nextLine(1, 3));
		methodBuf.append(String.format("this.%s = new java.util.LinkedList<>();", msgField.getDefName()));
		methodBuf.append(nextLine(1, 2));
		methodBuf.append("}");
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append(String.format("this.%s.add(data);", msgField.getDefName()));
		methodBuf.append(nextLine(1, 2));
		methodBuf.append("return this;");
		
		methodBuf.append(nextLine(1, 1));
		methodBuf.append("}");
		
		return methodBuf.toString();
	}
	
	public String addAllMethod() {
		StringBuffer methodBuf = new StringBuffer();
			
		methodBuf.append(nextLine(0, 1));
		methodBuf.append(String.format("public %s addAll%s(java.util.List<%s> datas) {", 
				msgBody.getMsgName(true), firstCharUpCase(msgField.getDefName()), getGenericType()));
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append(String.format("if (%s == null) {", msgField.getDefName()));
		methodBuf.append(nextLine(1, 3));
		methodBuf.append(String.format("this.%s = new java.util.LinkedList<>();", msgField.getDefName()));
		methodBuf.append(nextLine(1, 2));
		methodBuf.append("}");
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append(String.format("this.%s.addAll(datas);", msgField.getDefName()));
		methodBuf.append(nextLine(1, 2));
		methodBuf.append("return this;");

		methodBuf.append(nextLine(1, 1));
		methodBuf.append("}");
			
		return methodBuf.toString();
	}
	
	public String getDefineType() {
		switch (msgField.getDefType()) {
		case MESSAGE: return msgField.getMessageType();
		default:	  return JAVA_TYPE.get(msgField.getDefType()).basicType();
		}
	}

	public String getGenericType() {
		/*begin 如果定义类型不为vector,不存在包装类型*/
		if (msgField.getDefType() != FieldType.VECTOR) {
			return null;
		}/*end*/
		
		
		/*begin 定义类型为voctor且为消息类型,返回定义的类型*/
		if (msgField.getGenericType() == FieldType.MESSAGE) {
			return msgField.getMessageType();
		}/*end*/

		/*begin 返回基础类型的包装类型*/
		return JAVA_TYPE.get(msgField.getGenericType()).wrappType();
	}

	private String getDefendWithGeneric() {
		StringBuffer buf = new StringBuffer();
		
		if (msgField.getDefType() == FieldType.VECTOR) {
			buf.append("java.util.List<").append(getGenericType()).append('>');
		} else {
			buf.append(getDefineType());
		}
		
		return buf.toString();
	}
}
