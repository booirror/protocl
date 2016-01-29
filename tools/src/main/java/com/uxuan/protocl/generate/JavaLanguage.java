package com.uxuan.protocl.generate;

import com.uxuan.protocl.Language;

public class JavaLanguage implements Language {

	@Override
	public String BOOL(boolean wrap) {
		return wrap ? "Boolean" : "boolean";
	}

	@Override
	public String INT8(boolean wrap) {
		return wrap ? "Byte" : "byte";
	}

	@Override
	public String INT16(boolean wrap) {
		return wrap ? "Short" : "short";
	}

	@Override
	public String INT32(boolean wrap) {
		return wrap ? "Integer" : "int";
	}

	@Override
	public String INT64(boolean wrap) {
		return wrap ? "Long" : "long";
	}

	@Override
	public String FLOAT(boolean wrap) {
		return wrap ? "Float" : "float";
	}

	@Override
	public String DOUBLE(boolean wrap) {
		return wrap ? "Double" : "double";
	}

	@Override
	public String STRING() {
		return "String";
	}

	@Override
	public String ARRAY() {
		return "java.util.List";
	}

	@Override
	public String SET() {
		return "java.util.Set";
	}

	@Override
	public String MAP() {
		return "java.util.Map";
	}

}
