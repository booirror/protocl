package com.uxuan.protocl;

public enum AttrType {
	
	/**
	 * boolean
	 */
	BOOL {
		@Override
		public String type() {
			return "bool";
		}
	},
	
	/**
	 * 字节
	 */
	INT8 {
		@Override
		public String type() {
			return "int8";
		}
	},

	/**
	 * 短整型
	 */
	INT16 {
		@Override
		public String type() {
			return "int16";
		}
	},

	/**
	 * 整型
	 */
	INT32 {
		@Override
		public String type() {
			return "int32";
		}
	},

	/**
	 * 长整型
	 */
	INT64 {
		@Override
		public String type() {
			return "int64";
		}
	},

	/**
	 * 单精度
	 */
	FLOAT32 {
		@Override
		public String type() {
			return "float32";
		}
	},
	
	/**
	 * 双精度
	 */
	FLOAT64 {
		@Override
		public String type() {
			return "float64";
		}
	},
	
	/**
	 * 字符串
	 */
	STRING {
		@Override
		public String type() {
			return "string";
		}
	},
	
	/**
	 * 枚举类型
	 */
	ENUM {
		@Override
		public String type() {
			return null;
		}
	},
	
	/**
	 * 消息类型
	 */
	MESSAGE {
		@Override
		public String type() {
			return null;
		}
	},
	
	/**
	 * 有序集合
	 */
	ARRAY {
		@Override
		public String type() {
			return "array";
		}
	},
	
	/**
	 * 无序集合
	 */
	SET {
		@Override
		public String type() {
			return "set";
		}
	},
	
	/**
	 * 字典
	 */
	MAP {
		@Override
		public String type() {
			return "map";
		}
	},
	;
	
	private AttrType() {
	}

	public abstract String type();
	
	@Override
	public String toString() {
		return type();
	}
}
