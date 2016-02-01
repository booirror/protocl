package com.uxuan.protocl;

public enum MessageType {

	/**
	 * 枚举
	 */
	ENUM {
		@Override
		public String type() {
			return "enum";
		}
	},
	
	/**
	 * 消息
	 */
	MESSAGE {
		@Override
		public String type() {
			return "message";
		}
	},

	;
	
	private MessageType() {
	}

	public abstract String type();
	
	@Override
	public String toString() {
		return type();
	}
}
