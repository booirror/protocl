package com.godwan.code.util;

public final class CodeUtil {
	
	public static String firstCharUpCase(String fileName) {
		if (fileName == null || fileName.isEmpty()) {
			throw new RuntimeException("file name can not null");
		}

		String fistKey = fileName.substring(0, 1).toUpperCase();
		String suffix = fileName.substring(1, fileName.length());

		return String.format("%s%s", fistKey, suffix);
	}
	
	public static String firstCharLowerCase(String fileName) {
		if (fileName == null || fileName.isEmpty()) {
			throw new RuntimeException("file name can not null");
		}

		String fistKey = fileName.substring(0, 1).toLowerCase();
		String suffix = fileName.substring(1, fileName.length());

		return String.format("%s%s", fistKey, suffix);
	}
}
