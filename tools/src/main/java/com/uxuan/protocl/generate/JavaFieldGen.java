package com.uxuan.protocl.generate;


import static com.uxuan.protocl.util.StringUtils.firstCharUpCase;
import static com.uxuan.protocl.util.StringUtils.nextLine;

import com.uxuan.protocl.ProtoclAttr;
import com.uxuan.protocl.ProtoclAttr.AttrType;
import com.uxuan.protocl.SupportType;

public class JavaFieldGen extends SupportType {

	/** 消息body*/
	private final ProtoclAttr attr;
	
	
	public JavaFieldGen(ProtoclAttr attr) {
		super(JavaLanguage.class);
		this.attr = attr;
	}

	public String genAttribute() {
		return attr.isEnum() ? enumAttr() : fieldAttr();
	}
	
	public String genGetMethod() {
		return null;
	}
	
	public String genSetMethod() {
		return null;
	}
	
	public String addMethod() {
		return null;
	}
	
	public String addAllMethod() {
		return null;
	}
	
	public String putMethod() {
		return null;
	}
	
	public String putAllMethod() {
		return null;
	}
	
	private String enumAttr() {
		StringBuilder buf = new StringBuilder();
		buf.append("\n\t");
		buf.append(attr.getAttrName()).append(" {");
		buf.append("\n\t\t");
		buf.append("public int value() {");
		buf.append("\n\t\t\t");
		buf.append("return ").append(attr.getAttrIndex()).append(";");
		buf.append("\n\t");
		buf.append("},");
		
		return buf.toString();
	}
	
	private String fieldAttr() {
		StringBuilder buf = new StringBuilder();
		buf.append("\n\t");
		buf.append("private ");
		
		AttrType type = attr.getDefType();
		if (type.getGenericTypes().isEmpty()) {
			buf.append(basicType(type.getDefType()));
		} else if (type.getGenericTypes().size() == 1) {
			buf.append(basicType(type.getDefType()));
			buf.append('<').append(wrapType(type.getGenericTypes().get(0))).append('>');
		} else {
			buf.append(basicType(type.getDefType()));
			buf.append('<').append(wrapType(type.getGenericTypes().get(0))).append(',');
			buf.append(wrapType(type.getGenericTypes().get(1))).append('>');
		}
		
		buf.append(' ');
		buf.append(attr.getAttrName());
		buf.append(';');
		
		return  buf.toString();
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
	
}
