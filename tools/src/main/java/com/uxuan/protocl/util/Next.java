package com.uxuan.protocl.util;

/**
 * 换行，空格
 *
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月10日 下午7:48:46
 */
public interface Next {

	default String nextLine(int lineSize) {
		StringBuilder nextBuf = new StringBuilder();

		for (int i = 0; i < lineSize; i++) {
			nextBuf.append('\n');
		}
		
		return nextBuf.toString();
	}
	
	default String nextTable(int tableSize) {
		StringBuilder nextBuf = new StringBuilder();

		for (int i = 0; i < tableSize; i++) {
			nextBuf.append('\t');
		}
		
		return nextBuf.toString();
	}
	
	default String next(int lineSize, int tableSize) {
		StringBuilder nextBuf = new StringBuilder();
		
		for (int i = 0; i < lineSize; i++) {
			nextBuf.append('\n');
		}

		for (int i = 0; i < tableSize; i++) {
			nextBuf.append('\t');
		}
		
		return nextBuf.toString();
	}
}
