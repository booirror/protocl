package com.uxuan.protocl;

import com.uxuan.soty.gen.JavaLanguage;

/**
 * 类型管理
 * 
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月10日 下午8:02:40
 */
public class LanguageManager {
	
	private static final LanguageManager instance = new LanguageManager();
	
	public static LanguageManager getInstance() {
		return instance;
	}
	
	private Language language =  new JavaLanguage();
	
	public void init(Class<? extends Language> clazz) {
		try {
			this.language = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	public AttrType getType(String defind) {
		if (AttrType.BOOL.isType(defind)) { 		
			return AttrType.BOOL;
		}
		
		if (AttrType.INT8.isType(defind)) { 		
			return AttrType.INT8;
		}
		
		if (AttrType.INT16.isType(defind)) { 		
			return AttrType.INT16;
		}
		
		if (AttrType.INT32.isType(defind)) { 		
			return AttrType.INT32;
		}
		
		if (AttrType.INT64.isType(defind)) { 		
			return AttrType.INT64;
		}
		
		if (AttrType.FLOAT32.isType(defind)) { 		
			return AttrType.FLOAT32;
		}
		
		if (AttrType.FLOAT64.isType(defind)) { 		
			return AttrType.FLOAT64;
		}
		
		if (AttrType.STRING.isType(defind)) { 		
			return AttrType.STRING;
		}
		
		if (AttrType.ARRAY.isType(defind)) {		
			return AttrType.ARRAY;
		}
		
		if (AttrType.SET.isType(defind)) {			
			return AttrType.SET;
		}
		
		if (AttrType.MAP.isType(defind)) {			
			return AttrType.MAP;
		}
		
		return null;
	}
	
	public Attribute getAttribute(AttrType type) {
		switch (type) {
		case BOOL: 		return language.BOOL();
		case INT8: 		return language.INT8();
		case INT16: 	return language.INT16();
		case INT32: 	return language.INT32();
		case INT64: 	return language.INT64();
		case FLOAT32: 	return language.FLOAT32();
		case FLOAT64: 	return language.FLOAT64();
		case STRING: 	return language.STRING();
		case ENUM:      return language.ENUM();
		case MESSAGE:   return language.MESSAGE();
		case ARRAY:		return language.ARRAY();
		case SET:		return language.SET();
		case MAP:		return language.MAP();
		default: throw new RuntimeException("unsupport type:" + type);
		}
	}
	
	
}
