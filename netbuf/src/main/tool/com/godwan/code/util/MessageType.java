package com.godwan.code.util;


public enum MessageType {
	
	MESSAGE_REQUEST("<-  [客->服]", 0 | 1),
	MESSAGE_RESPONSE("-> [服->客]", 0 | 2);
	
	private final String name;
	private final int value;
	
	MessageType(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public static MessageType valueOf(int value) {
		switch (value) {
		case 0 | 1: return MESSAGE_REQUEST;
		case 0 | 2: return MESSAGE_RESPONSE;
		default: throw new RuntimeException("unkown type:" + value);
		}
	}
}