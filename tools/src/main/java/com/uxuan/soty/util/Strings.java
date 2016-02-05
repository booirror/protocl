package com.uxuan.soty.util;

public final class Strings {
	
	/**
	 * <p>第一个字母大写</p>
	 * @param str 
	 * @return
	 */
	public static String firstCharUpCase(String str) {
		if (str == null || str.isEmpty()) {
			throw new RuntimeException("file name can not null");
		}

		String fistKey = str.substring(0, 1).toUpperCase();
		String suffix = str.substring(1, str.length());

		return String.format("%s%s", fistKey, suffix);
	}
	
	/**
	 * <p>第一个字母小写</p>
	 * @param str
	 * @return
	 */
	public static String firstCharLowerCase(String str) {
		if (str == null || str.isEmpty()) {
			throw new RuntimeException("file name can not null");
		}

		String fistKey = str.substring(0, 1).toLowerCase();
		String suffix = str.substring(1, str.length());

		return String.format("%s%s", fistKey, suffix);
	}
	
	private Strings() {
		throw new RuntimeException("can't new intance");
	}
}
