package org.itas.buffer.tools.client.cpp;

import java.util.EnumMap;

import org.itas.buffer.tools.MsgField;
import org.itas.buffer.tools.MsgFiledType.FieldType;

public class CppField  {
	
	private static final EnumMap<FieldType, String> CPP_TYPE = new EnumMap<FieldType, String>(FieldType.class) {
		private static final long serialVersionUID = -6861525540105806338L;

		{
			put(FieldType.BOOL, "bool");
			put(FieldType.INT8, "byte");
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

	public String getDefinedType() {
		if (msgField.getDefClassType() == FieldType.MESSAGE) {
			return msgField.getDefFieldName();
		}
		
		return CPP_TYPE.get(msgField.getDefClassType());
	}
}
