package com.godwan.code;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileService {

	public static List<File> getFiles(String s) {
		return getFiles("./", s);
	}

	public static List<File> getFiles(String dir, String s) {
		File file = new File(dir);

		s = s.replace('.', '#');
		s = s.replaceAll("#", "\\\\.");
		s = s.replace('*', '#');
		s = s.replaceAll("#", ".*");
		s = s.replace('?', '#');
		s = s.replaceAll("#", ".?");
		s = "^" + s + "$";

		Pattern p = Pattern.compile(s);
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