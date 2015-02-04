package org.itas.buffer.tools;

import org.itas.buffer.tools.MsgFiledType.FieldType;


public class MsgField {
	
	/**
	 * 属性类型
	 */
	private FieldType defClassType;
	
	/**
	 * 属性类型名称
	 */
	private String defClassTypeName;

	/**
	 * vector泛型类型
	 */
	private FieldType defGenericClassType;
	
	/**
	 * 属性名称
	 */
	private String defFieldName;
	
	public MsgField(Finder finder) {
		this.defClassTypeName = finder.group(1);
		this.defClassType = FieldType.parse(defClassTypeName);
		this.defGenericClassType = FieldType.parse(finder.group(3));;
		this.defFieldName = finder.group(4);
	}
	
	public FieldType getDefClassType() {
		return defClassType;
	}
	
	public String getDefClassTypeName() {
		return defClassTypeName;
	}

	public FieldType getDefGenericClassType() {
		return defGenericClassType;
	}

	public String getDefFieldName() {
		return defFieldName;
	}

	@Override
	public String toString() {
		return String.format("%s%s %s;", defClassType.def(), getGenericTypeStr(), defFieldName);
	}
	
	private String getGenericTypeStr() {
		if (defGenericClassType == null) {
			return "";
		}
		
		return String.format("<%s>", defGenericClassType.def());
	}
}
