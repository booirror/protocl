package com.uxuan.protocl;

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
	
	private Language language =  new DefaultLanguage();
	
	public void init(Class<? extends Language> clazz) {
		try {
			this.language = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
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
	
	private static class DefaultLanguage implements Language {
		
		private static final Attribute ATTRBOOL = Attribute.newBuilder().setType(AttrType.BOOL).build();
		private static final Attribute ATTRINT8 = Attribute.newBuilder().setType(AttrType.INT8).build();
		private static final Attribute ATTRINT16 = Attribute.newBuilder().setType(AttrType.INT16).build();
		private static final Attribute ATTRINT32 = Attribute.newBuilder().setType(AttrType.INT32).build();
		private static final Attribute ATTRINT64 = Attribute.newBuilder().setType(AttrType.INT64).build();
		private static final Attribute FLOAT32 = Attribute.newBuilder().setType(AttrType.FLOAT32).build();
		private static final Attribute FLOAT64 = Attribute.newBuilder().setType(AttrType.FLOAT64).build();
		private static final Attribute STRING = Attribute.newBuilder().setType(AttrType.STRING).build();
		private static final Attribute ENUM = Attribute.newBuilder().setType(AttrType.ENUM).build();
		private static final Attribute MESSAGE = Attribute.newBuilder().setType(AttrType.MESSAGE).build();
		private static final Attribute ARRAY = Attribute.newBuilder().setType(AttrType.ARRAY).build();
		private static final Attribute SET = Attribute.newBuilder().setType(AttrType.SET).build();
		private static final Attribute MAP = Attribute.newBuilder().setType(AttrType.SET).build();

		@Override
		public Attribute BOOL() {
			return ATTRBOOL;
		}

		@Override
		public Attribute INT8() {
			return ATTRINT8;
		}

		@Override
		public Attribute INT16() {
			return ATTRINT16;
		}

		@Override
		public Attribute INT32() {
			return ATTRINT32;
		}

		@Override
		public Attribute INT64() {
			return ATTRINT64;
		}

		@Override
		public Attribute FLOAT32() {
			return FLOAT32;
		}

		@Override
		public Attribute FLOAT64() {
			return FLOAT64;
		}

		@Override
		public Attribute STRING() {
			return STRING;
		}

		@Override
		public Attribute ENUM() {
			return null;
		}

		@Override
		public Attribute MESSAGE() {
			return null;
		}

		@Override
		public Attribute ARRAY() {
			return ARRAY;
		}

		@Override
		public Attribute SET() {
			return SET;
		}

		@Override
		public Attribute MAP() {
			return MAP;
		}

	}
	
}
