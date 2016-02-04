package com.uxuan.protocl;

import java.util.Objects;

import com.uxuan.protocl.module.ProtoclMsg;

public final class Attribute {
	
	/** 协议定义属性*/
	private final AttrType type;
	
	/** 自定义类型*/
	private final ProtoclMsg msg;
	
	/** 语音基础属性*/
	private final String base;
	
	/** 基础属性对应包装类型(面向对象语音)*/
	private final String wrap;
	
	/** 定义属性创建实列*/
	private final String create;
	
	private Attribute(AttrType define, ProtoclMsg msg, String base, String wrap, String create) {
		this.type = define;
		this.msg = msg;
		this.base = base;
		this.wrap = wrap;
		this.create = create;
	}
	
	public boolean isType(AttrType type) {
		return this.type == type;
	}
	
	public String getDefind() {
		if (Objects.isNull(msg)) {
			return type.value();
		}
		
		return msg.getName();
	}

	public String getBase() {
		return base;
	}

	public String getWrap() {
		return wrap;
	}

	public String getCreate() {
		return create;
	}
	
	public Builder toBuilder() {
		return newBuilder()
				.setType(type)
				.setMsg(msg)
				.setBase(base)
				.setWrap(wrap)
				.setCreate(create);
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static class Builder {
		
		private AttrType type;
		private ProtoclMsg msg;
		private String base;
		private String wrap;
		private String create;
		
		private Builder() {
		}
		
		public AttrType getType() {
			return type;
		}

		public Builder setType(AttrType type) {
			this.type = type;
			return this;
		}
		
		public ProtoclMsg getMsg() {
			return msg;
		}

		public Builder setMsg(ProtoclMsg msg) {
			this.msg = msg;
			return this;
		}

		public String getBase() {
			return base;
		}
		
		public Builder setBase(String base) {
			this.base = base;
			return this;
		}
		
		public String getWrap() {
			return wrap;
		}
		
		public Builder setWrap(String wrap) {
			this.wrap = wrap;
			return this;
		}
		
		public String getCreate() {
			return create;
		}
		
		public Builder setCreate(String create) {
			this.create = create;
			return this;
		}
		
		public Attribute build() {
			return new Attribute(type, msg, base, wrap, create);
		}
		
	}
	
	
}
