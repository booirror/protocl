package org.itas.tool.netbuf;


public interface MsgFiledType {

	public enum FieldType {
		BOOL {
			@Override
			public String def() {
				return "bool";
			}
		},
		INT8 {
			@Override
			public String def() {
				return "int8";
			}
		},
		INT16 {
			@Override
			public String def() {
				return "int16";
			}
		},
		INT {
			@Override
			public String def() {
				return "int";
			}
		},
		INT64 {
			@Override
			public String def() {
				return "int64";
			}
		},
		FLOAT {
			@Override
			public String def() {
				return "float";
			}
		},
		DOUBLE {
			@Override
			public String def() {
				return "double";
			}
		},
		STRING {
			@Override
			public String def() {
				return "string";
			}
		},
		VECTOR {
			@Override
			public String def() {
				return "vector";
			}
		},
		MESSAGE {
			@Override
			public String def() {
				return "";
			}
		},
		;
		
		public abstract String def();
		
		public static FieldType parse(String type) {
			if (type == null || "".equals(type)) {
				return null;
			}
			
			switch (type) {
			case "bool": 	return BOOL;
			case "int8": 	return INT8;
			case "int16": 	return INT16;
			case "int": 	return INT;
			case "int64": 	return INT64;
			case "float": 	return FLOAT;
			case "double": 	return DOUBLE;
			case "string": 	return STRING;
			case "vector": 	return VECTOR;
			default:		return MESSAGE;
			}
		}
	}
	
	/**
	 * 基本类型
	 * @return
	 */
	public abstract String basicType(); 
	
	/**
	 * 包装类型
	 * @return
	 */
	public abstract String wrappType();
	
	/**
	 * 定义类型
	 * @return
	 */
	public abstract FieldType defType();
	
}
