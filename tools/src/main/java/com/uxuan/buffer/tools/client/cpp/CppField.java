package com.uxuan.buffer.tools.client.cpp;

import java.util.EnumMap;

import com.uxuan.buffer.tools.MsgField;
import com.uxuan.buffer.tools.AbstreactFieldType;
import com.uxuan.buffer.tools.MsgFiledType.FieldType;

public class CppField extends AbstreactFieldType {
	
	private static final EnumMap<FieldType, String> CPP_TYPE = new EnumMap<FieldType, String>(FieldType.class) {
		private static final long serialVersionUID = -6861525540105806338L;
		{
			put(FieldType.BOOL, "bool");
			put(FieldType.INT8, "char");
			put(FieldType.INT16, "short");
			put(FieldType.INT, "int");
			put(FieldType.INT64, "long long");
			put(FieldType.FLOAT, "float");
			put(FieldType.DOUBLE, "double");
			put(FieldType.STRING, "String");
			put(FieldType.VECTOR, "vector");
			put(FieldType.MESSAGE, "");
		}
	};
	
	private MsgField msgField;
	
	public CppField(MsgField msgField) {
		this.msgField = msgField;
	}

	@Override
	public String getVectorGenericClassTypeName() {
		if (msgField.getDefClassType() != FieldType.VECTOR) {
			return null;
		}
		
		switch (msgField.getDefGenericClassType()) {
		case MESSAGE:  return msgField.getDefGenericClassTypeName();
		default:	   return CPP_TYPE.get(msgField.getDefGenericClassType());
		}
	}
	
	@Override
	public String getWholeDefineClassTypeName() {
		switch (msgField.getDefClassType()) {
		case VECTOR:  return "vector<" + getVectorGenericClassTypeName() + '>';
		case MESSAGE: return msgField.getDefClassTypeName();
		default:	  return CPP_TYPE.get(msgField.getDefClassType());
		}
	}
}
