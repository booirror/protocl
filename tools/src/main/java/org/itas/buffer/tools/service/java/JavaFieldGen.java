package org.itas.buffer.tools.service.java;


import static org.itas.buffer.tools.util.StringUtils.firstCharUpCase;
import static org.itas.buffer.tools.util.StringUtils.nextLine;

import java.util.EnumMap;

import org.itas.buffer.tools.MsgBody;
import org.itas.buffer.tools.MsgField;
import org.itas.buffer.tools.MsgFiledType.FieldType;

public class JavaFieldGen  {
	
	private static final EnumMap<FieldType, JavaFiledType> JAVA_TYPE = new EnumMap<FieldType, JavaFiledType>(FieldType.class) {
		private static final long serialVersionUID = -6861525540105806338L;

		{
			put(FieldType.BOOL, new JavaFiledType("boolean", "Boolean"));
			put(FieldType.INT8, new JavaFiledType("byte", "Byte"));
			put(FieldType.INT16, new JavaFiledType("short", "Short"));
			put(FieldType.INT, new JavaFiledType("int", "Integer"));
			put(FieldType.INT64, new JavaFiledType("long", "Long"));
			put(FieldType.FLOAT, new JavaFiledType("float", "Float"));
			put(FieldType.DOUBLE, new JavaFiledType("double", "Double"));
			put(FieldType.STRING, new JavaFiledType("String", "String"));
			put(FieldType.VECTOR, new JavaFiledType("List", "List"));
			put(FieldType.MESSAGE, new JavaFiledType("", ""));
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
		return String.format("private %s %s;", getDefendWithGeneric(), msgField.getDefFieldName());
	}
	
	public String getMethod() {
		StringBuffer methodBuf = new StringBuffer();
		
		methodBuf.append(nextLine(0, 1));
		methodBuf.append(String.format("public %s get%s()", getDefendWithGeneric(), firstCharUpCase(msgField.getDefFieldName())));
		methodBuf.append(" {");
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append(String.format("return this.%s;", msgField.getDefFieldName()));
		
		methodBuf.append(nextLine(1, 1));
		methodBuf.append("}");
		
		return methodBuf.toString();
	}
	
	public String setMethod() {
		StringBuffer methodBuf = new StringBuffer();
		
		methodBuf.append(nextLine(0, 1));
		methodBuf.append(String.format("public %s set%s(%s data) {", 
				msgBody.getMsgName(true), firstCharUpCase(msgField.getDefFieldName()), getDefendWithGeneric()));
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append(String.format("this.%s = data;", msgField.getDefFieldName()));
		
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
				msgBody.getMsgName(true), firstCharUpCase(msgField.getDefFieldName()), getDefendWithGeneric()));
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append(String.format("this.%s.set(index, data);", msgField.getDefFieldName()));
		
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
				msgBody.getMsgName(true), firstCharUpCase(msgField.getDefFieldName()), getGenericType()));
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append(String.format("if (%s == null) {", msgField.getDefFieldName()));
		methodBuf.append(nextLine(1, 3));
		methodBuf.append(String.format("this.%s = new java.util.LinkedList<>();", msgField.getDefFieldName()));
		methodBuf.append(nextLine(1, 2));
		methodBuf.append("}");
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append(String.format("this.%s.add(data);", msgField.getDefFieldName()));
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
				msgBody.getMsgName(true), firstCharUpCase(msgField.getDefFieldName()), getGenericType()));
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append(String.format("if (%s == null) {", msgField.getDefFieldName()));
		methodBuf.append(nextLine(1, 3));
		methodBuf.append(String.format("this.%s = new java.util.LinkedList<>();", msgField.getDefFieldName()));
		methodBuf.append(nextLine(1, 2));
		methodBuf.append("}");
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append(String.format("this.%s.addAll(datas);", msgField.getDefFieldName()));
		methodBuf.append(nextLine(1, 2));
		methodBuf.append("return this;");

		methodBuf.append(nextLine(1, 1));
		methodBuf.append("}");
			
		return methodBuf.toString();
	}
	
	public String getDefineType() {
		switch (msgField.getDefClassType()) {
		case MESSAGE: return msgField.getDefClassTypeName();
		default:	  return JAVA_TYPE.get(msgField.getDefClassType()).basicType();
		}
	}

	public String getGenericType() {
		/*begin 如果定义类型不为vector,不存在包装类型*/
		if (msgField.getDefClassType() != FieldType.VECTOR) {
			return null;
		}/*end*/
		
		
		/*begin 定义类型为voctor且为消息类型,返回定义的类型*/
		if (msgField.getDefGenericClassType() == FieldType.MESSAGE) {
			return msgField.getDefClassTypeName();
		}/*end*/

		/*begin 返回基础类型的包装类型*/
		return JAVA_TYPE.get(msgField.getDefGenericClassType()).wrappType();
	}

	private String getDefendWithGeneric() {
		StringBuffer buf = new StringBuffer();
		
		if (msgField.getDefClassType() == FieldType.VECTOR) {
			buf.append("java.util.List<").append(getGenericType()).append('>');
		} else {
			buf.append(getDefineType());
		}
		
		return buf.toString();
	}
}
