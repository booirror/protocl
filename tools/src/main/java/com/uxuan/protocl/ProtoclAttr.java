package com.uxuan.protocl;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义属性
 *
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月28日 下午9:10:46
 */
public class ProtoclAttr {

	/** 属性类型*/
	private final AttrType defType;
	
	/** 属性名称*/
	private final String attrName;
	
	/** 序号*/
	private final int attrIndex;
	
	private final ProtoclMsg protoclMsg;
	
	public ProtoclAttr(ProtoclMsg protoclMsg, String attrName, int attrIndex) {
		this(protoclMsg, null, attrName, attrIndex);
	}
	
	public ProtoclAttr(ProtoclMsg protoclMsg, AttrType defType, String attrName) {
		this(protoclMsg, defType, attrName, -1);
	}
	
	public ProtoclAttr(ProtoclMsg protoclMsg, AttrType defType, String attrName, int attrIndex) {
		this.protoclMsg = protoclMsg;
		this.defType = defType;
		this.attrName = attrName;
		this.attrIndex = attrIndex;
	}
	
	public AttrType getDefType() {
		return defType;
	}

	public String getAttrName() {
		return attrName;
	}

	public int getAttrIndex() {
		return attrIndex;
	}
	
	public String getMsgName() {
		return protoclMsg.getName();
	}
	
	public boolean isEnum() {
		return defType == null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (defType == null) {
			builder.append(attrName).append(" = ").append(attrIndex).append(";");
		} else  {
			builder.append(defType.toString()).append(' ').append(attrName).append(";");
		}
		
		return builder.toString();
	}
	
	public static class AttrType {
		
		/** 定义类型*/
		private String defType;
		
		/** 泛型类型*/
		private List<String> genericTypes;
		
		public AttrType() {
			this(null);
		}
		
		public AttrType(String defType) {
			this.defType = defType;
			this.genericTypes = new ArrayList<String>();
		}
		
		public void setDefType(String type) {
			this.defType = type;
		}

		public String getDefType() {
			return defType;
		}

		public List<String> getGenericTypes() {
			return genericTypes;
		}
		
		public void addGeneric(String type) {
			this.genericTypes.add(type);
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder(defType);
			
			if (genericTypes.size() == 1) {
				builder.append('<').append(genericTypes.get(0)).append('>');
			} else if (genericTypes.size() == 2) {
				builder.append('<').append(genericTypes.get(0)).append(',').append(genericTypes.get(1)).append('>');
			}
			
			return builder.toString();
		}
	}
	
}
