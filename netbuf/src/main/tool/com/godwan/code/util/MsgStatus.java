package com.godwan.code.util;

/**
 * <p>消息类型</p>
 * 客户端到服务器:{@link #CLIENT_TO_SERVER}<br />
 * 服务器到客户端:{@link #SERVER_TO_CLIENT}<br />
 * 服务器到客户端双向:{@link #CLIENT_TO_SERVER}&{@link #SERVER_TO_CLIENT}
 * @author liuzhen(liuxing521a@gmail.com)
 * @crateTime 2015年2月2日下午4:04:52
 */
public enum MsgStatus {
	/**
	 * [客->服]
	 */
	CLIENT_TO_SERVER {

		@Override
		public String getName() {
			return "<-";
		}

		@Override
		public int getValue() {
			return 1;
		}
		
	},
	
	/**
	 * [服->客]
	 */
	SERVER_TO_CLIENT {

		@Override
		public String getName() {
			return "->";
		}

		@Override
		public int getValue() {
			return 2;
		}
		
	},
	;
	
	/**
	 * 消息类型名
	 * @return
	 */
	public abstract String getName();

	/**
	 * 详细类型对应值
	 * @return
	 */
	public abstract int getValue();

	public static MsgStatus valueOf(int value) {
		switch (value) {
		case 1: return CLIENT_TO_SERVER;
		case 2: return SERVER_TO_CLIENT;
		default: throw new RuntimeException("unkown type:" + value);
		}
	}
}