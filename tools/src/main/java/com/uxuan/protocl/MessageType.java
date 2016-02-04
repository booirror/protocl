package com.uxuan.protocl;

public enum MessageType {
	
	/**
	 * 包
	 */
	PACKAGE {
		@Override
		public String value() {
			return "package";
		}
	},
	
	/**
	 * 导入
	 */
	IMPORT {
		@Override
		public String value() {
			return "import";
		}
	},

	/**
	 * 枚举
	 */
	ENUM {
		@Override
		public String value() {
			return "enum";
		}
	},
	
	/**
	 * 消息
	 */
	MESSAGE {
		@Override
		public String value() {
			return "message";
		}
	},
	;
	
	private MessageType() {
	}

	public abstract String value();
	
	public boolean isType(String defind) {
		return value().equals(defind);
	}
}
