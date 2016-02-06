package com.uxuan.soty.module;

import java.util.ArrayList;
import java.util.List;

import com.uxuan.soty.util.Constant.DefindType;

/**
 * 定义属性
 *
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月28日 下午9:10:46
 */
public class SoAttribute {
	
	/** 属性类型*/
	private final SoDefinition type;
	
	/** 泛型列表*/
	private final List<SoDefinition> genericTypes;
	
	/** 属性名称*/
	private final String name;
	
	/** 序号*/
	private final int index;
	
	
	private SoAttribute(SoDefinition type, List<SoDefinition> genericTypes, String name, int index) {
		this.type = type;
		this.genericTypes = genericTypes;
		this.name = name;
		this.index = index;
	}
	
	public SoDefinition getType() {
		return type;
	}

	public List<SoDefinition> getGenericTypes() {
		return genericTypes;
	}

	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}

	public String getGenericAttribute(int index) {
		return genericTypes.get(index).getTypeName();
	}
	
	@Override
	public String toString() {
		if (type.isType(DefindType.BOOL) ||
			type.isType(DefindType.INT8) ||
			type.isType(DefindType.INT16) ||
			type.isType(DefindType.INT32) ||
			type.isType(DefindType.INT64) ||
			type.isType(DefindType.FLOAT32) ||
			type.isType(DefindType.FLOAT64) ||
			type.isType(DefindType.STRING) || 
			type.isType(DefindType.MESSAGE) ||
			type.isType(null) ) {
			return String.format("%s %s;", type.getTypeName(), name);
		}
		
		if (type.isType(DefindType.ENUM)) {
			return String.format("%s = %s;", name, index);
		}
		
		if (type.isType(DefindType.ARRAY)) {
			return String.format("%s<%s>, %s;", type.getTypeName(), getGenericAttribute(0), name);
		} 
		
		if (type.isType(DefindType.SET)) {
			return String.format("%s<%s>, %s;", type.getTypeName(), getGenericAttribute(0), name);
		} 
		
		if (type.isType(DefindType.MAP)) {
			return String.format("%s<%s, %s>, %s;", type.getTypeName(), getGenericAttribute(0), getGenericAttribute(1), name);
		} 
		
		throw new RuntimeException("Unsupport Type:" + type.getTypeName());
	}
	
	public Builder toBuilder() {
		return newBuilder()
				.setType(type)
				.setGenericTypes(genericTypes)
				.setName(name)
				.setIndex(index);
	}
	
	public static Builder newBuilder() {
		return new Builder(); 
	}
	
	public static class Builder {
		
		private SoDefinition type;
		private List<SoDefinition> genericTypes;
		private String name;
		private int index;
		
		private Builder() {
		}

		public SoDefinition getType() {
			return type;
		}

		public Builder setType(SoDefinition type) {
			this.type = type;
			return this;
		}

		public List<SoDefinition> getGenericTypes() {
			return genericTypes;
		}

		public Builder setGenericTypes(List<SoDefinition> genericTypes) {
			this.genericTypes = genericTypes;
			return this;
		}
		
		public Builder addGeneric(SoDefinition attribute) {
			if (this.genericTypes == null) {
				this.genericTypes = new ArrayList<>();
			}
			
			this.genericTypes.add(attribute);
			return this;
		}

		public String getName() {
			return name;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public int getIndex() {
			return index;
		}

		public Builder setIndex(int index) {
			this.index = index;
			return this;
		}
		
		public SoAttribute build() {
			return new SoAttribute(type, genericTypes, name, index);
		}
		
	}
	
}
