package com.godwan.code.lang;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.godwan.code.FieldInfo;

public class CppField  {
	
	private static final Map<String, String> DEFINED_CPP_BASIC = Collections.unmodifiableMap(new HashMap<String, String>() {
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
			put("vector", "vector");
		}
	});
	
	private FieldInfo fieldInto;
	
	public CppField(FieldInfo fieldInto) {
		this.fieldInto = fieldInto;
	}

	public String getDefinedType() {
		return DEFINED_CPP_BASIC.get(fieldInto.getClassType());
	}

	public FieldInfo getFieldInto() {
		return fieldInto;
	}
	
	
}
