package com.uxuan.soty.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.uxuan.protocl.util.DefineException;

/***
 * 行内容
 * 
 * @author liuzhen
 */
public final class SoLine {
	
	private final String fileName;
	/** 行号*/
	private final int lineNum;
	/** 内容*/
	private final String content;
	
	public SoLine(String fineName, int lineNum, String line) {
		this.fileName = fineName;
		this.lineNum = lineNum;
		this.content = subExegesis(fineName, lineNum, line);
	}

	/**
	 * 获取文件名	
	 * 
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 获取行号
	 * 
	 * @return
	 */
	public int getLineNum() {
		return lineNum;
	}

	/**
	 * 获取列
	 * 
	 * @return
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * 是否为注释行或者空行
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return content.length() == 0;
	}
	
	/**
	 * 定义内容分组
	 * 
	 * @return
	 */
	public List<String> toGroup() {
		if (isEmpty()) {
			return Collections.emptyList();
		}
		
		char[] chs = content.toCharArray();
		List<String> groups = new ArrayList<String>();
		
		StringBuilder buf = new StringBuilder();
		for (char ch : chs) {
			if (ch == ' ') {
				if (buf.length() > 0)
				groups.add(buf.toString());
				buf = new StringBuilder();
			} else if (ch == '{') {
				if (buf.length() > 0)
				groups.add(buf.toString());
				groups.add("{");
				buf = new StringBuilder();
			} else if (ch == '}') {
				if (buf.length() > 0)
				groups.add(buf.toString());
				groups.add("}");
				buf = new StringBuilder();
			} else if (ch == ';') {
				if (buf.length() > 0)
				groups.add(buf.toString());
				groups.add(";");
				buf = new StringBuilder();
			} else if (ch == '=') {
				if (buf.length() > 0)
				groups.add(buf.toString());
				buf = new StringBuilder();
			} else {
				buf.append(ch);
			}
		}
		
		if (buf.length() > 0) {
			groups.add(buf.toString());
		}
		
		return Collections.unmodifiableList(groups);
	}

	/***
	 * 去除注释部分
	 * 
	 * @param fileName 文件名
	 * @param lineIndex 行号
	 * @param line 文本
	 * @return 去除注释后的内容
	 */
	private String subExegesis(final String fileName, final int lineIndex, String line) {
		char[] chs = line.trim().toCharArray();

		StringBuffer keeper = new StringBuffer();
		for (int i = 0; i < chs.length; i ++) {
			if (chs[i] == '/') {
				checkExegesis(fileName, lineIndex, i, chs);
				break;
			}
			
			keeper.append(chs[i]);
		}
		
		return keeper.toString().trim();
	}
	
	/**
	 * 检查是否合法注释
	 * 
	 * @param fileName 文件名
	 * @param lineIndex 行号
	 * @param column 列号
	 * @param chs 文本组成的Character
	 */
	private void checkExegesis(final String fileName, final int lineIndex, final int column, char[] chs) {
		if (chs.length <= column || chs[column + 1] != '/') {
			throw new DefineException("protocl:%s [line=%s], [column=%s]", fileName, lineIndex, column);
		}
	}
	
}
