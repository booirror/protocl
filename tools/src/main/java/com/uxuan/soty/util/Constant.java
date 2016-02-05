package com.uxuan.soty.util;

public class Constant {
	
	/** 协议文本内容*/
	public static enum ContentType {
		
		/** 包*/
		PACKAGE ("package"),
		
		/**导入*/
		IMPORT ("import"),

		/** 枚举*/
		ENUM ("enum"),
		
		/** 消息*/
		MESSAGE ("message"),
		;
		
		public final String value;
		
		private ContentType(String value) {
			this.value = value;
		}
		
		public boolean isType(String defind) {
			return value.equals(defind);
		}
		
		@Override
		public String toString() {
			return value;
		}
	}

	/** 定义属性类型*/
	public static enum DefindType {
		
		/** boolean*/
		BOOL ("bool"),
		
		/** 字节*/
		INT8 ("int8"),

		/** 短整型*/
		INT16 ("int16"),

		/** 整型*/
		INT32 ("int32"),

		/** 长整型*/
		INT64 ("int64"),

		/** 单精度*/
		FLOAT32 ("float32"),
		
		/** 双精度*/
		FLOAT64 ("float64"),
		
		/**字符串*/
		STRING ("string"),
		
		/** 枚举类型*/
		ENUM (null),
		
		/** 消息类型*/
		MESSAGE (null),
		
		/** 有序集合 */
		ARRAY ("array"),
		
		/** 无序集合*/
		SET ("set"),
		
		/** 字典*/
		MAP ("map"),
		;
		
		public final String value;
		
		private DefindType(String value) {
			this.value = value;
		}

		public boolean isType(String defind) {
			return value.startsWith(defind);
		}
		
		@Override
		public String toString() {
			return value;
		}
		
	}
}
