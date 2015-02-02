package com.godwan.code.lang;


import static com.godwan.code.util.Strings.firstCharUpCase;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.godwan.code.ClassInfo;
import com.godwan.code.FieldInfo;

public class JavaField  {
	
	private static final Map<String, String> DEFINED_JAVA_BASIC = Collections.unmodifiableMap(new HashMap<String, String>() {
		private static final long serialVersionUID = -4255579607383368055L;
		{
			put("bool", "boolean");
			put("int8", "byte");
			put("char", "char");
			put("int16", "short");
			put("int", "int");
			put("int64", "long");
			put("float", "float");
			put("double", "double");
			put("string", "String");
			put("vector", "List");
		}
	});
	
	private static final Map<String, String> DEFINED_JAVA_PACK = Collections.unmodifiableMap(new HashMap<String, String>() {
		private static final long serialVersionUID = -4255579607383368055L;
		{
			put("boolean", "Boolean");
			put("int8", "Byte");
			put("char", "Character");
			put("int16", "Short");
			put("int", "Integer");
			put("int64", "Long");
			put("float", "Float");
			put("double", "Double");
			put("string", "String");
		}
	});
	
	private ClassInfo classInfo;
	private FieldInfo fieldInto;
	
	public JavaField(ClassInfo classInfo, FieldInfo fieldInfo) {
		this.classInfo = classInfo;
		this.fieldInto = fieldInfo;
	}

	public String getDefendLine() {
		StringBuffer lineBuf = new StringBuffer();
		
		lineBuf.append("private ");
		lineBuf.append(getGenericDefend());
		lineBuf.append(" ");
        lineBuf.append(fieldInto.getFieldName());
        lineBuf.append(";");
         
        return lineBuf.toString();
	}
	
	public String getGetMethod() {
		StringBuffer methodBuf = new StringBuffer();
		methodBuf.append("\t");
		methodBuf.append(String.format("public %s get%s()", getGenericDefend(), firstCharUpCase(fieldInto.getFieldName())));
		methodBuf.append(" {");
		methodBuf.append("\n\t\t");
		methodBuf.append(String.format("return this.%s;", fieldInto.getFieldName()));
		methodBuf.append("\n\t}\n\n");
		
		return methodBuf.toString();
	}
	
	public String getSetMethod() {
		StringBuffer methodBuf = new StringBuffer();
		methodBuf.append("\t");
		methodBuf.append(String.format("public %s set%s(%s %s)", 
				classInfo.getClassName(true), firstCharUpCase(fieldInto.getFieldName()), getGenericDefend(), fieldInto.getFieldName()));
		methodBuf.append(" {");
		methodBuf.append("\n\t\t");
		methodBuf.append(String.format("this.%s = %s;", fieldInto.getFieldName(), fieldInto.getFieldName()));
		methodBuf.append("\n\t\treturn this;");
		methodBuf.append("\n\t}\n\n");
		
		return methodBuf.toString();
	}
	
	public String getDefineType() {
		String definedType = DEFINED_JAVA_BASIC.get(fieldInto.getClassType());
		if (definedType == null) {
			definedType = firstCharUpCase(fieldInto.getClassType());
		}
		
		return definedType;
	}

	public String getGenericType() {
		String genericType = DEFINED_JAVA_PACK.get(fieldInto.getGenericName());
		if (genericType == null) {
			genericType = firstCharUpCase(fieldInto.getGenericName());
		}
		
		return genericType;
	}

	public boolean isBasicType() {
		return DEFINED_JAVA_PACK.containsKey(fieldInto.getClassType());
	}
	
	public String getGenericDefend() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(getDefineType());
		if (fieldInto.getGenericName() != null) {
			buf.append('<').append(getGenericType()).append('>');
		}
		
		return buf.toString();
	}
}
