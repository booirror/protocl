package com.uxuan.protocl.module;

import java.util.ArrayList;
import java.util.List;

import com.uxuan.protocl.AttrType;
import com.uxuan.protocl.Attribute;

/**
 * 定义属性
 *
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月28日 下午9:10:46
 */
public class ProtoclAttr {
	
	/** 属性属于的Message*/
	private final ProtoclMsg message;

	/** 属性类型*/
	private final Attribute type;
	
	/** 泛型列表*/
	private final List<Attribute> genericTypes;
	
	/** 属性名称*/
	private final String name;
	
	/** 序号*/
	private final int index;
	
	
	private ProtoclAttr(ProtoclMsg message, Attribute type, List<Attribute> genericTypes, String name, int index) {
		this.message = message;
		this.type = type;
		this.genericTypes = genericTypes;
		this.name = name;
		this.index = index;
	}
	
	public ProtoclMsg getMessage() {
		return message;
	}

	public Attribute getType() {
		return type;
	}

	public List<Attribute> getGenericTypes() {
		return genericTypes;
	}

	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}

	public Attribute getGenericAttribute(int index) {
		return genericTypes.get(index);
	}
	
	@Override
	public String toString() {
		if (type.isType(AttrType.BOOL) ||
			type.isType(AttrType.INT8) ||
			type.isType(AttrType.INT16) ||
			type.isType(AttrType.INT32) ||
			type.isType(AttrType.INT64) ||
			type.isType(AttrType.FLOAT32) ||
			type.isType(AttrType.FLOAT64) ||
			type.isType(AttrType.STRING) || 
			type.isType(AttrType.MESSAGE)) {
			return String.format("%s %s;", type.getDefind(), name);
		}
		
		if (type.isType(AttrType.ENUM)) {
			return String.format("%s = %s;", name, index);
		}
		
		if (type.isType(AttrType.ARRAY)) {
			return String.format("%s<%s>, %s;", type.getDefind(), getGenericAttribute(0).getDefind(), name);
		} 
		
		if (type.isType(AttrType.SET)) {
			return String.format("%s<%s>, %s;", type.getDefind(), getGenericAttribute(0).getDefind(), name);
		} 
		
		if (type.isType(AttrType.MAP)) {
			return String.format("%s<%s, %s>, %s;", type.getDefind(), getGenericAttribute(0).getDefind(), getGenericAttribute(1).getDefind(), name);
		} 
		
		throw new RuntimeException("Unsupport Type:" + type.getDefind());
	}
	
	public Builder toBuilder() {
		return newBuilder()
				.setMessage(message)
				.setType(type)
				.setGenericTypes(genericTypes)
				.setName(name)
				.setIndex(index);
	}
	
	public static Builder newBuilder() {
		return new Builder(); 
	}
	
	public static class Builder {
		
		private ProtoclMsg message;
		private Attribute type;
		private List<Attribute> genericTypes;
		private String name;
		private int index;
		
		private Builder() {
		}

		public ProtoclMsg getMessage() {
			return message;
		}

		public Builder setMessage(ProtoclMsg message) {
			this.message = message;
			return this;
		}

		public Attribute getType() {
			return type;
		}

		public Builder setType(Attribute type) {
			this.type = type;
			return this;
		}

		public List<Attribute> getGenericTypes() {
			return genericTypes;
		}

		public Builder setGenericTypes(List<Attribute> genericTypes) {
			this.genericTypes = genericTypes;
			return this;
		}
		
		public Builder addGeneric(Attribute attribute) {
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
		
		public ProtoclAttr build() {
			return new ProtoclAttr(message, type, genericTypes, name, index);
		}
		
	}
	
}
