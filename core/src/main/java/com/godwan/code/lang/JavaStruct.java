package com.godwan.code.lang;

import static com.godwan.code.util.CodeUtil.firstCharUpCase;
import com.godwan.code.ClassInfo;
import com.godwan.code.FieldInfo;
import com.godwan.code.util.MessageType;

public class JavaStruct {

	private ClassInfo classInfo;
	
	public JavaStruct(ClassInfo clazz) {
		this.classInfo = clazz;
	}
	
	public String getClazz() {
		StringBuffer classdBuf = new StringBuffer();
		
		classdBuf.append("public static class ");
		classdBuf.append(classInfo.getClassName(true));
		classdBuf.append(" extends net.itas.core.Msg ");
		if (classInfo.contains(MessageType.MESSAGE_RESPONSE)) {
			classdBuf.append(" implements net.itas.core.io.SendAble ");
		}
		
		classdBuf.append("{");
		classdBuf.append("\n");
		classdBuf.append(getDefinedStruct());

		classdBuf.append("\n\n");
		classdBuf.append(getConstructor());

		classdBuf.append("\n\n");
		classdBuf.append(getGetAndSet());

		classdBuf.append(getListOprateMethod());
	        
		classdBuf.append(getTobufferMethod());

		classdBuf.append("\n\n");
		classdBuf.append(getParseBufferMethod());
	        
		classdBuf.append("\n\n");
		classdBuf.append(bufToString());

		classdBuf.append(getSendMsg());

		classdBuf.append("\n\n");
		classdBuf.append(getNewBuilder());

		classdBuf.append("\n\n");
		classdBuf.append(getParseFrom());

	        
		classdBuf.append("}\n");
	        
        return classdBuf.toString();
	}
	
	public String getMessage() {
		StringBuffer contentBuf = new StringBuffer();
		if (classInfo.contains(MessageType.MESSAGE_REQUEST)) {
			contentBuf.append("\n\n\t");
			contentBuf.append("static final short Event_");
			contentBuf.append(classInfo.getClassName(false));
			contentBuf.append(" = ");
			contentBuf.append(classInfo.getHexMethod());
			contentBuf.append(";");
		}

		if (classInfo.contains(MessageType.MESSAGE_RESPONSE)) {
			
			contentBuf.append("\n\n\t");
			contentBuf.append("static final short Event_");
			contentBuf.append(classInfo.getClassName(false));
			contentBuf.append(" = ");
			contentBuf.append(classInfo.getHexMessage());
			contentBuf.append(";");
		}
		
		return contentBuf.toString();
	}
	
	public String getRequestMessage() {
		StringBuffer contentBuf = new StringBuffer();
		if (classInfo.contains(MessageType.MESSAGE_REQUEST)) {
			contentBuf.append("\n\n\t");
			contentBuf.append("public abstract void ");
			contentBuf.append(classInfo.getClassName(false));
			contentBuf.append(" = ");
			contentBuf.append(classInfo.getHexMethod());
			contentBuf.append(";");
		}

		return contentBuf.toString();
	}
	
	public String getSendMsg() {
		StringBuffer msgBuf = new StringBuffer();
		
		if (classInfo.contains(MessageType.MESSAGE_RESPONSE)) {
			msgBuf.append("\n\n\tpublic Message getData() {");
			msgBuf.append("\n\t\treturn Message.allocate(Event_");
			msgBuf.append(classInfo.getClassName(false));
			msgBuf.append(", this);");
			msgBuf.append("\n\t}");
		}
		
		return msgBuf.toString();
	}
	
	private String getDefinedStruct() {
		StringBuffer fieldBuf = new StringBuffer();
		
		JavaField javaField;
		for (FieldInfo field : classInfo.getFieldList()) {
			javaField = new JavaField(classInfo, field);
			fieldBuf.append("\n\t");
			fieldBuf.append(javaField.getDefendLine());
		}
		
		return fieldBuf.toString();
	}
	
	private String getConstructor() {
		StringBuffer consBuf = new StringBuffer();
	
		consBuf.append("\tprivate ");
		consBuf.append(classInfo.getClassName(true));
		consBuf.append("() {\n\t}");

		return consBuf.toString();
	}
	
	private String getGetAndSet() {
		StringBuffer methodBuf = new StringBuffer();

		JavaField javaField;
		for (FieldInfo field : classInfo.getFieldList()) {
			javaField = new JavaField(classInfo, field);
			methodBuf.append(javaField.getGetMethod());
			methodBuf.append(javaField.getSetMethod());
		}
		
		return methodBuf.toString();
	}
	
	private String getListOprateMethod() {
		StringBuffer methodBuf = new StringBuffer();
		
		JavaField javaField;
		for (FieldInfo field : classInfo.getFieldList()) {
			if (field.getGenericName() != null) {
				javaField = new JavaField(classInfo, field);
				methodBuf.append("\t");
				
				methodBuf.append("public " + classInfo.getClassName(true) + " add");
				methodBuf.append(firstCharUpCase(field.getFieldName()));
				methodBuf.append("(");
				methodBuf.append(javaField.getGenericType());
				methodBuf.append(" data) {");
				
				methodBuf.append("\n\t\tif (");
				methodBuf.append(field.getFieldName());
				methodBuf.append(" == null) {");
				methodBuf.append("\n\t\t\tthis.");
				methodBuf.append(field.getFieldName());
				methodBuf.append(" = new LinkedList<");
				methodBuf.append(javaField.getGenericType());
				methodBuf.append(">(); \n\t\t}");
				
				methodBuf.append("\n\t\tthis.");
				methodBuf.append(field.getFieldName());
				methodBuf.append(".add(data);");
				methodBuf.append("\n\t\treturn this;");
				methodBuf.append("\n\t}");
				
				
				
				methodBuf.append("\n\n\t");
				methodBuf.append("public " + classInfo.getClassName(true) + " addAll");
				methodBuf.append(firstCharUpCase(field.getFieldName()));
				methodBuf.append("(List<");
				methodBuf.append(javaField.getGenericType());
				methodBuf.append("> dataList) {");
				
				methodBuf.append("\n\t\tif (");
				methodBuf.append(field.getFieldName());
				methodBuf.append(" == null) {");
				methodBuf.append("\n\t\t\tthis.");
				methodBuf.append(field.getFieldName());
				methodBuf.append(" = new LinkedList<");
				methodBuf.append(javaField.getGenericType());
				methodBuf.append(">(); \n\t\t}");
				
				methodBuf.append("\n\t\tthis.");
				methodBuf.append(field.getFieldName());
				methodBuf.append(".addAll(dataList);");
				methodBuf.append("\n\t\treturn this;");
				methodBuf.append("\n\t}");
				
				
				methodBuf.append("\n\n\tpublic " + classInfo.getClassName(true) + " set");
				methodBuf.append(firstCharUpCase(field.getFieldName()));
				methodBuf.append("(int index, ");
				methodBuf.append(javaField.getGenericType());
				methodBuf.append(" data) {");
				
				methodBuf.append("\n\t\tif (");
				methodBuf.append(field.getFieldName());
				methodBuf.append(" == null) {");
				methodBuf.append("\n\t\t\tthis.");
				methodBuf.append(field.getFieldName());
				methodBuf.append(" = new LinkedList<");
				methodBuf.append(javaField.getGenericType());
				methodBuf.append(">(); \n\t\t}");
				
				methodBuf.append("\n\t\tthis.");
				methodBuf.append(field.getFieldName());
				methodBuf.append(".set(index, data);");
				methodBuf.append("\n\t\treturn this;");
				methodBuf.append("\n\t}\n\n");
			}
		}
		
		return methodBuf.toString();
	}
	
	private String getParseBufferMethod() {
		StringBuffer toBuf = new StringBuffer();

		toBuf.append("\t@Override");
		toBuf.append("\n\t");
		toBuf.append("protected ");
		toBuf.append(classInfo.getClassName(true));
		toBuf.append(" parseBuffer(ByteBuf buf) {");
		
		JavaField javaField;
		for (FieldInfo field : classInfo.getFieldList()) {
			javaField = new JavaField(classInfo, field);
			
			toBuf.append("\n\t\tthis.");
			toBuf.append(field.getFieldName());
			toBuf.append(" = ");

			if (field.getGenericName() != null) {
				toBuf.append("readArray(buf, ");
	        	toBuf.append(javaField.getGenericType());
	        	toBuf.append(".class");
	        } else if (javaField.isBasicType()) {
	        	toBuf.append("read");
	        	toBuf.append(field.getClassType());
	        	toBuf.append("(buf");
	        } else {
	        	toBuf.append(firstCharUpCase(field.getClassType()));
	        	toBuf.append(".parseFrom(buf");
	        }

			toBuf.append(");");
			
		}
		
		toBuf.append("\n\n\t\treturn this;");
		toBuf.append("\n\t}");
		return toBuf.toString();
	}
	
	private String getTobufferMethod() {
		StringBuffer pareBuf = new StringBuffer();
		
		pareBuf.append("\t@Override");
		pareBuf.append("\n\t");
		pareBuf.append("public void toBuffer(ByteBuf buf) {");
		
		JavaField javaField;
		for (FieldInfo field : classInfo.getFieldList()) {
			javaField = new JavaField(classInfo, field);

			pareBuf.append("\n\t\t");
			if (field.getGenericName() != null) {
				pareBuf.append("writeArray(buf, ");
			} else if (javaField.isBasicType()) {
				pareBuf.append("write");
				pareBuf.append(field.getClassType());
				pareBuf.append("(buf, ");
			} else {
				pareBuf.append("writeMessage(buf, ");
			}
			
			pareBuf.append(field.getFieldName());
			pareBuf.append(");");
		}
		 
		pareBuf.append("\n\t}");
		return pareBuf.toString();
	}
	
	protected String getReadObject() {
		StringBuffer buildBuf = new StringBuffer();
		
		buildBuf.append("\t@Override\n\tprotected ");
		buildBuf.append(classInfo.getClassName(true));
		buildBuf.append(" readObject(ByteBuf buf) {");
		buildBuf.append("\n\t\treturn ");
		buildBuf.append(classInfo.getClassName(true));
		buildBuf.append(".parseFrom(buf);");
		buildBuf.append("\n\t}");
		
		return buildBuf.toString(); 
	}
	
	protected String getNewInstance() {
		StringBuffer buildBuf = new StringBuffer();
		
		buildBuf.append("\t@Override\n\tprotected ");
		buildBuf.append(classInfo.getClassName(true));
		buildBuf.append(" newInstance() {");
		buildBuf.append("\n\t\treturn newBuilder();");
		buildBuf.append("\n\t}");
		
		return buildBuf.toString(); 
	}

	private String getNewBuilder() {
		StringBuffer buildBuf = new StringBuffer();
		
		buildBuf.append("\tpublic static ");
		buildBuf.append(classInfo.getClassName(true));
		buildBuf.append(" newBuilder() {");
		buildBuf.append("\n\t\t");
		buildBuf.append("return new ");
		buildBuf.append(classInfo.getClassName(true));
		buildBuf.append("();\n\t}");
		
		return buildBuf.toString(); 
	}
	
	private String getParseFrom() {
		StringBuffer buildBuf = new StringBuffer();
		
		buildBuf.append("\tpublic static ");
		buildBuf.append(classInfo.getClassName(true));
		buildBuf.append(" parseFrom(ByteBuf buf) {");
		
		buildBuf.append("\n\t\t");
		buildBuf.append(classInfo.getClassName(true));
		buildBuf.append(" data = new ");
		buildBuf.append(classInfo.getClassName(true));
		buildBuf.append("();");
		
		buildBuf.append("\n\t\t");
		buildBuf.append("data.parseBuffer(buf);");
		
		buildBuf.append("\n\t\t");
		buildBuf.append("return data;");
		buildBuf.append("\n\t}\n");
		
		return buildBuf.toString(); 
	}
	
	private String bufToString() {
		StringBuffer strBuf = new StringBuffer();
		
		strBuf.append("\t@Override");
		strBuf.append("\n\t");
		strBuf.append("public String toString() {");
		strBuf.append("\n\t\tStringBuffer strBuf = new StringBuffer();");
		strBuf.append("\n\t\tstrBuf.append(\"");
		strBuf.append(classInfo.getClassName(true));
		strBuf.append("{\");");
		
		JavaField javaField;
		for (FieldInfo field : classInfo.getFieldList()) {
			javaField = new JavaField(classInfo, field);
			
			if (field.getGenericName() != null) {
				strBuf.append("\n\t\tif (");
				strBuf.append(field.getFieldName());
				strBuf.append(" != null) {");
				strBuf.append("\n\t\t\tstrBuf.append(\"\\n");
				strBuf.append(field.getFieldName());
				strBuf.append(":\");");
				strBuf.append("\n\t\t\tfor (");
				strBuf.append(javaField.getGenericType());
				strBuf.append(" data : ");
				strBuf.append(field.getFieldName());
				strBuf.append(") {");
				strBuf.append("\n\t\t\t\tstrBuf.append(data);");
				strBuf.append("\n\t\t\t\tstrBuf.append(\",\\n\");");
				strBuf.append("\n\t\t\t}");
				strBuf.append("\n\t\t}");

			} else {
				strBuf.append("\n\t\tstrBuf.append(\"\\n\\t");
				strBuf.append(field.getFieldName());
				strBuf.append(":\").append(");
				strBuf.append(field.getFieldName());
				strBuf.append(");");
			}
		 }
		strBuf.append("\n\t\tstrBuf.append(\"\\n}\");");
		 
		strBuf.append("\n\n\t\treturn strBuf.toString();");
		strBuf.append("\n\t}");
		return strBuf.toString();
	}
	

}
