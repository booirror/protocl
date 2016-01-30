package com.uxuan.protocl;

public abstract class SupportType {

	private Language language;
	
	protected SupportType(Class<? extends Language> clazz) {
		try {
			language = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	protected String wrapType(String defType) {
		if ("bool".equals(defType)) {
			return language.BOOL(true);
		}
		
		if ("int8".equals(defType)) {
			return language.INT8(true);
		}
		
		if ("int16".equals(defType)) {
			return language.INT16(true);
		}

		if ("int16".equals(defType)) {
			return language.INT16(true);
		}
		
		if ("int32".equals(defType)) {
			return language.INT32(true);
		}
		
		if ("int64".equals(defType)) {
			return language.INT64(true);
		}

		if ("float".equals(defType)) {
			return language.FLOAT(true);
		}

		if ("double".equals(defType)) {
			return language.DOUBLE(true);
		}
		
		if ("string".equals(defType)) {
			return language.STRING(true);
		}
		
		if ("array".equals(defType)) {
			return language.ARRAY(true);
		}
		
		if ("set".equals(defType)) {
			return language.SET(true);
		}
		
		if ("map".equals(defType)) {
			return language.MAP(true);
		}
		
		return defType;
	}
	
	protected String basicType(String defType) {
		if ("bool".equals(defType)) {
			return language.BOOL(false);
		}
		
		if ("int8".equals(defType)) {
			return language.INT8(false);
		}
		
		if ("int16".equals(defType)) {
			return language.INT16(false);
		}

		if ("int16".equals(defType)) {
			return language.INT16(false);
		}
		
		if ("int32".equals(defType)) {
			return language.INT32(false);
		}
		
		if ("int64".equals(defType)) {
			return language.INT64(false);
		}

		if ("float".equals(defType)) {
			return language.FLOAT(false);
		}

		if ("double".equals(defType)) {
			return language.DOUBLE(false);
		}
		
		if ("string".equals(defType)) {
			return language.STRING(false);
		}
		
		if ("array".equals(defType)) {
			return language.ARRAY(false);
		}
		
		if ("set".equals(defType)) {
			return language.SET(false);
		}
		
		if ("map".equals(defType)) {
			return language.MAP(false);
		}
		
		return defType;
	}
	
}
