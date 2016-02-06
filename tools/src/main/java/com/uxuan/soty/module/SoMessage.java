package com.uxuan.soty.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.uxuan.soty.util.Constant.ContentType;

/**
 *
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月28日 下午9:10:26
 */
public class SoMessage {

	/** message name */
	private final String name;
	
	/** 是否枚举类型*/
	private final ContentType type;
	
	/** message attributes*/
	private final List<SoAttribute> attributes;
	
	private SoMessage(ContentType type, String name, List<SoAttribute> attributes) {
		this.type = type;
		this.name = name;
		this.attributes = Collections.unmodifiableList(attributes);
	}
	

	public boolean isType(ContentType type) {
		return type == this.type;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the attributes
	 */
	public List<SoAttribute> getAttributes() {
		return attributes;
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		if (isType(ContentType.ENUM)) {
			buf.append("enum ").append(name);
		} else {
			buf.append("message ").append(name);
		}
		
		buf.append(" {");
		for (SoAttribute attr : attributes) {
			buf.append("\n\t").append(attr);
		}
		
		buf.append("\n");
		buf.append("}");
		
		return buf.toString();
	}
	
	public Builder toBuilder() {
		return newBuilder()
				.setType(type)
				.setName(name)
				.setAttributes(new ArrayList<SoAttribute>(attributes));
	}
	
	public static Builder newBuilder() {
		return new Builder(); 
	}
	
	public static class Builder {
		
		private String name;
		private ContentType type;
		private List<SoAttribute> attributes;
		
		private Builder() {
			this.attributes = new ArrayList<SoAttribute>();
		}

		
		public String getName() {
			return name;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public ContentType getType() {
			return type;
		}

		public Builder setType(ContentType type) {
			this.type = type;
			return this;
		}

		public List<SoAttribute> getAttributes() {
			return attributes;
		}

		public Builder setAttributes(List<SoAttribute> attributes) {
			this.attributes = attributes;
			return this;
		}

		public Builder addAttribute(SoAttribute attribute) {
			this.attributes.add(attribute);
			return this;
		}

		public SoMessage build() {
			return new SoMessage(type, name, attributes);
		}
	}
	
}
