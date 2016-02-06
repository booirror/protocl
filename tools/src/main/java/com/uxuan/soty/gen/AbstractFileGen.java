package com.uxuan.soty.gen;

import java.util.Set;

public abstract class AbstractFileGen {
	
	/**
	 * 生成包名或者文件夹名称
	 * 
	 * @param pkg 名称
	 * @return
	 */
	protected abstract String genPackage(String pkg);
	
	/**
	 * 包含文件或者导入文件等
	 * 
	 * @param include
	 * @return
	 */
	protected abstract String genInclude(Set<String> include);
	
	/**
	 * 是否生成多个文件
	 * 
	 * @return
	 */
	protected abstract boolean isMuilt();
	
	/**
	 * 语言结构体开始
	 * 
	 * @return
	 */
	protected abstract String languageBegin();
	
	/**
	 * 语言结构体结束
	 * 
	 * @return
	 */
	protected abstract String languageEnd();
}
