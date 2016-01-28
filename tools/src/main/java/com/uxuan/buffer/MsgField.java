package com.uxuan.buffer;

public class MsgField {
	
	/** 属性类型*/
	private final DefType defType;
	/** 属性类型*/
	private DefType genericType;
	/** 属性名称*/
	private final String defName;
	
	public MsgField(Finder finder) {
		this.defType = DefType.parse(finder.group(1));
		if (DefType.MESSAGE == defType || DefType.VECTOR == defType) {
			System.out.println(finder.group(1) + "," + finder.group(3));
			this.genericType = DefType.parse(finder.group(3));
		}
		this.defName = finder.group(4);
	}
	
	/**
	 * @return the defType
	 */
	public DefType getDefType() {
		return defType;
	}

	/**
	 * @return the genericType
	 */
	public DefType getGenericType() {
		return genericType;
	}

	/**
	 * @return the defName
	 */
	public String getDefName() {
		return defName;
	}
	
	public boolean isArrayExist() {
		return defType == DefType.VECTOR;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (DefType.MESSAGE == defType) {
			builder.append(genericType);
		} else if (DefType.VECTOR == defType) {
			builder.append(defType).append('<').append(genericType).append('>');
		} else {
			builder.append(defType);
		}
		builder.append(' ').append(defName);
		
		return builder.toString();
	}
}
