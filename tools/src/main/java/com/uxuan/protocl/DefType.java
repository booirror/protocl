package com.uxuan.protocl;

/**
 * 定义支持类型
 * 
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月10日 下午8:02:40
 */
public abstract class DefType {
	
	/**boolean类型*/
	public static final DefType BOOL = load("bool");
	/**char类型*/
	public static final DefType CHAR = load("char");
	/**byte类型*/
	public static final DefType INT8 = load("int8");
	/**short类型*/
	public static final DefType INT16 = load("int16");
	/**int类型*/
	public static final DefType INT = load("int");
	/**long类型*/
	public static final DefType INT64 = load("int64");
	/**float类型*/
	public static final DefType FLOAT = load("float");
	/**double类型*/
	public static final DefType DOUBLE = load("double");
	/**string类型*/
	public static final DefType STRING = load("string");
	/**vector类型*/
	public static final DefType VECTOR = load("vector");
	/**vector类型*/
	public static final DefType ENUM = load("enum");
	/**object类型*/
	public static final DefType MESSAGE = load("object");
	
	private final String name;
	
	protected DefType(String name) {
		this.name = name;
	}
	
	public String getDefType() {
		return null;
	}
		
	private static DefType load(String type) {
		return null;
	}
	
	public static DefType parse(String type) {
		switch (type) {
		case "bool": 	return BOOL;
		case "char": 	return CHAR;
		case "int8": 	return INT8;
		case "int16": 	return INT16;
		case "int": 	return INT;
		case "int64": 	return INT64;
		case "float": 	return FLOAT;
		case "double": 	return DOUBLE;
		case "string": 	return STRING;
		case "vector": 	return VECTOR;
		case "enum": 	return ENUM;
		case "object": 	return MESSAGE;
		default: throw new RuntimeException("unsuppot type:" + type);
		}
	}
}
