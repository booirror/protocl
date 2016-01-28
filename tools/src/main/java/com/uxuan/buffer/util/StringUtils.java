package com.uxuan.buffer.util;

public final class StringUtils {
	
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
	
	public static String nextLine(int lineZise, int tableSize) {
		StringBuilder nextBuf = new StringBuilder();
		
		for (int i = 0; i < lineZise; i++) {
			nextBuf.append('\n');
		}

		for (int i = 0; i < tableSize; i++) {
			nextBuf.append('\t');
		}
		
		return nextBuf.toString();
	}
	
	private StringUtils() {
		throw new RuntimeException("can't new intance");
	}
}
