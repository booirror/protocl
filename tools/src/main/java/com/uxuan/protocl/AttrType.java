package com.uxuan.protocl;

public enum AttrType {
	
	/**
	 * boolean
	 */
	BOOL {
		@Override
		public String value() {
			return "bool";
		}
	},
	
	/**
	 * 字节
	 */
	INT8 {
		@Override
		public String value() {
			return "int8";
		}
	},

	/**
	 * 短整型
	 */
	INT16 {
		@Override
		public String value() {
			return "int16";
		}
	},

	/**
	 * 整型
	 */
	INT32 {
		@Override
		public String value() {
			return "int32";
		}
	},

	/**
	 * 长整型
	 */
	INT64 {
		@Override
		public String value() {
			return "int64";
		}
	},

	/**
	 * 单精度
	 */
	FLOAT32 {
		@Override
		public String value() {
			return "float32";
		}
	},
	
	/**
	 * 双精度
	 */
	FLOAT64 {
		@Override
		public String value() {
			return "float64";
		}
	},
	
	/**
	 * 字符串
	 */
	STRING {
		@Override
		public String value() {
			return "string";
		}
	},
	
	/**
	 * 枚举类型
	 */
	ENUM {
		@Override
		public String value() {
			return null;
		}
	},
	
	/**
	 * 消息类型
	 */
	MESSAGE {
		@Override
		public String value() {
			return null;
		}
	},
	
	/**
	 * 有序集合
	 */
	ARRAY {
		@Override
		public String value() {
			return "array";
		}
	},
	
	/**
	 * 无序集合
	 */
	SET {
		@Override
		public String value() {
			return "set";
		}
	},
	
	/**
	 * 字典
	 */
	MAP {
		@Override
		public String value() {
			return "map";
		}
	},
	;
	
	private AttrType() {
	}

	public abstract String value();
	
	public boolean isType(String defind) {
		return value().equals(defind);
	}
	
}
