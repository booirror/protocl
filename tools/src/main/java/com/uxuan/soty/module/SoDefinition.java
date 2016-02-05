package com.uxuan.soty.module;

import com.uxuan.soty.util.Constant.DefindType;

/**
 * 协议定义的属性类型
 * 
 * @author liuzhen
 *
 */
public final class SoDefinition {

	/** 定义类型*/
	private final DefindType type;
	
	/** 定义类型名称*/
	private final String typeName;
	
	private SoDefinition(DefindType type, String typeName) {
		this.type = type;
		this.typeName = typeName;
	}
	
	public boolean isType(DefindType type) {
		return this.type == type;
	}

	public DefindType getType() {
		return type;
	}

	public String getTypeName() {
		return typeName;
	}

	public Builder toBuilder() {
		return newBuilder().setType(type).setTypeName(typeName);
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static class Builder {
		
		private DefindType type;
		
		private String typeName;
		
		private Builder() {
		}

		public DefindType getType() {
			return type;
		}

		public Builder setType(DefindType type) {
			this.type = type;
			return this;
		}

		public String getTypeName() {
			return typeName;
		}

		public Builder setTypeName(String typeName) {
			this.typeName = typeName;
			return this;
		}
		
		public SoDefinition build() {
			return new SoDefinition(type, typeName);
		}
	}

	
}
