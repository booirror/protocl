package com.uxuan.protocl.util;

import java.util.regex.Pattern;

/**
 * 
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月28日 下午9:27:06
 */
public enum Finer {
	/** 
	 * 包名
	 */
	PACKAGE ("[ \t]*package[ \t](\\w+)[ \t]*"),
	
	/**
	 * 导入外部文件
	 */
	IMPORTFILES ("[ \t]*import[ \t]((\\w+)|(/))"),
	
	/**
	 * 注释
	 */
	COTENTNOTES ("^[ \t]*//"),
	
	/**
	 * 协议定义
	 */
	MESSAGEDEF ("^[ \t]*message[ \t]+(\\w+)[ \t]*([<]?)([-]?)([>]?)[ \t]*"),
	
	/**
	 * 协议属性定义
	 */
	MESSAGEFIELD ("(\\w+)[ \t]*(<(\\w+)>)?[ \t]*(\\w+)"),
	
	/**
	 * 协议结束标识
	 */
	MESSAGEEND ("^[ \t]*}");
	
	final Pattern pattern;
	
	private Finer(String regex) {
		pattern = Pattern.compile(regex);
	}
	
//	public static Finder matcher(String line) {
//		Matcher matcher;
//		for (MsgPat pat : MsgPat.values()) {
//			matcher = pat.pattern.matcher(line);
//			if (matcher.find()) {
//				return new Finder(pat, matcher);
//			}
//		}
//		
//		return null;
//	}
}
