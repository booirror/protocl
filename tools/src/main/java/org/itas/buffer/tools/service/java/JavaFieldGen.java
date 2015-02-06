package org.itas.buffer.tools.service.java;


import static org.itas.buffer.tools.util.StringUtils.firstCharUpCase;
import static org.itas.buffer.tools.util.StringUtils.nextLine;

import java.util.EnumMap;

import org.itas.buffer.tools.AbstreactFieldType;
import org.itas.buffer.tools.MsgBody;
import org.itas.buffer.tools.MsgField;
import org.itas.buffer.tools.MsgFiledType;
import org.itas.buffer.tools.MsgFiledType.FieldType;

public class JavaFieldGen extends AbstreactFieldType {
	
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
		return String.format("private %s %s;", getWholeDefineClassTypeName(), msgField.getDefFieldName());
	}
	
	public String getMethod() {
		StringBuffer methodBuf = new StringBuffer();
		
		methodBuf.append(nextLine(0, 1));
		methodBuf.append(String.format("public %s get%s()", getWholeDefineClassTypeName(), firstCharUpCase(msgField.getDefFieldName())));
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
				msgBody.getMsgName(true), firstCharUpCase(msgField.getDefFieldName()), getWholeDefineClassTypeName()));
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append(String.format("this.%s = data;", msgField.getDefFieldName()));
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append("return this;");
		
		methodBuf.append(nextLine(1, 1));
		methodBuf.append("}");
		
		return methodBuf.toString();
	}
	
	public String setVectorMethod() {
		if (msgField.getDefClassType() != FieldType.VECTOR) {
			return "";
		}
		
		StringBuffer methodBuf = new StringBuffer();
		
		methodBuf.append(nextLine(0, 1));
		methodBuf.append(String.format("public %s set%s(int index, %s data) {", 
				msgBody.getMsgName(true), firstCharUpCase(msgField.getDefFieldName()), getVectorGenericClassTypeName()));
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append(String.format("this.%s.set(index, data);", msgField.getDefFieldName()));
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append("return this;");
		
		methodBuf.append(nextLine(1, 1));
		methodBuf.append("}");
		
		return methodBuf.toString();
	}
	
	public String addVectorMethod() {
		if (msgField.getDefClassType() != FieldType.VECTOR) {
			return "";
		}
		
		StringBuffer methodBuf = new StringBuffer();
		
		methodBuf.append(nextLine(0, 1));
		methodBuf.append(String.format("public %s add%s(%s data) {", 
				msgBody.getMsgName(true), firstCharUpCase(msgField.getDefFieldName()), getVectorGenericClassTypeName()));
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append(String.format("if (this.%s == null) {", msgField.getDefFieldName()));
		methodBuf.append(nextLine(1, 3));
		methodBuf.append(String.format("this.%s = new ArrayList<>();", msgField.getDefFieldName()));
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
	
	public String addAllVectorMethod() {
		if (msgField.getDefClassType() != FieldType.VECTOR) {
			return "";
		}
		
		StringBuffer methodBuf = new StringBuffer();
			
		methodBuf.append(nextLine(0, 1));
		methodBuf.append(String.format("public %s addAll%s(%s datas) {", 
				msgBody.getMsgName(true), firstCharUpCase(msgField.getDefFieldName()), getWholeDefineClassTypeName()));
		
		methodBuf.append(nextLine(1, 2));
		methodBuf.append(String.format("if (this.%s == null) {", msgField.getDefFieldName()));
		methodBuf.append(nextLine(1, 3));
		methodBuf.append(String.format("this.%s = new ArrayList<>();", msgField.getDefFieldName()));
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
	
	@Override
	public String getVectorGenericClassTypeName() {
		if (msgField.getDefClassType() != FieldType.VECTOR) {
			return null;
		}
		
		switch (msgField.getDefGenericClassType()) {
		case MESSAGE:  return msgField.getDefGenericClassTypeName();
		default:	   return JAVA_TYPE.get(msgField.getDefGenericClassType()).wrappType();
		}
	}
	
	@Override
	public String getWholeDefineClassTypeName() {
		switch (msgField.getDefClassType()) {
		case VECTOR:  return "List<" + getVectorGenericClassTypeName() + '>';
		case MESSAGE: return msgField.getDefClassTypeName();
		default:	  return JAVA_TYPE.get(msgField.getDefClassType()).basicType();
		}
	}
	
	private static class JavaFiledType implements MsgFiledType {

		/**
		 * java基础类型
		 */
		private String basicType;
		
		/**
		 * java 包装类型
		 */
		private String wrappType;
		
		
		public JavaFiledType(String basicType, String wrappType) {
			this.basicType = basicType;
			this.wrappType = wrappType;
		}
		
		@Override
		public String basicType() {
			return basicType;
		}

		@Override
		public String wrappType() {
			return wrappType;
		}
	}
}
