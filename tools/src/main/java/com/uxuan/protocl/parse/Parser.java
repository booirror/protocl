package com.uxuan.protocl.parse;

/**
 * 定义解析器
 * 
 * @author liuzhen
 */
public interface Parser {
	
	/**
	 * 解析
	 * 
	 * @param line 解析行
	 */
	Parser parse(Line line);
	

}
