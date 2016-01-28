package com.uxuan.buffer.util;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {

	public static List<File> getFiles(String suffix) {
		return getFiles("./", suffix);
	}

	public static List<File> getFiles(String dir, String suffix) {
		File file = new File(dir);

		suffix = suffix.replace('.', '#');
		suffix = suffix.replaceAll("#", "\\\\.");
		suffix = suffix.replace('*', '#');
		suffix = suffix.replaceAll("#", ".*");
		suffix = suffix.replace('?', '#');
		suffix = suffix.replaceAll("#", ".?");
		
//		//^(?:\\w+\\.xlsx|\\w+\\.xls)$
		suffix = "^(?:\\w+\\" + suffix + ")$";

		Pattern p = Pattern.compile(suffix);
		List<File> list = filePattern(file, p);

		return list;
	}

	private static List<File> filePattern(File file, Pattern p) {
		if (file == null) {
			return null;
		}

		if (file.isFile()) {
			Matcher fMatcher = p.matcher(file.getName());
			if (fMatcher.matches()) {
				List<File> list = new LinkedList<File>();
				list.add(file);
				return list;
			}
		} else if (file.isDirectory()) {
			File[] files = file.listFiles();
			if ((files != null) && (files.length > 0)) {
				List<File> list = new LinkedList<File>();
				for (File f : files) {
					List<File> rlist = filePattern(f, p);
					if (rlist != null) {
						list.addAll(rlist);
					}
				}

				return list;
			}
		}

		return null;
	}
}