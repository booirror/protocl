package org.itas.buffer.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 定义类型查找这
 * @author liuzhen(liuxing521a@gmail.com)
 * @crateTime 2015年2月3日下午2:52:07
 */
public final class Finder {
	
	/**协议定义类型*/
	public enum MsgPat {
		/** 
		 * 包名
		 */
		PACKAGENAME ("[ \t]*package[ \t]((\\w+)|(/))"),
		
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
		
		private MsgPat(String regex) {
			pattern = Pattern.compile(regex);
		}
	}
	
	public static Finder matcher(String line) {
		Matcher matcher;
		for (MsgPat pat : MsgPat.values()) {
			matcher = pat.pattern.matcher(line);
			if (matcher.find()) {
				return new Finder(pat, matcher);
			}
		}
		
		return null;
	}
	
		
	/**
	 * 所属消息类型
	 */
	public final MsgPat pat;

	/**
	 * 匹配结果
	 */
	public final Matcher matcher;
	
	Finder(MsgPat pat, Matcher matcher) {
		this.pat = pat;
		this.matcher = matcher;
	}
	
	public String group(int group) {
		return matcher.group(group);
	}
		
}
