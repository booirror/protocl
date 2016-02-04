package com.uxuan.protocl.module;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 解析后文件
 *
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月28日 下午9:21:51
 */
public class Protocl {

	/** 所有解析协议文件*/
	private final Map<String, ProtoclFile> protocls;
	
	public Protocl() {
		protocls = new LinkedHashMap<String, ProtoclFile>();
	}
	
	public void addProtocl(ProtoclFile protocl) {
		protocls.put(protocl.getName(), protocl);
	}

	public Map<String, ProtoclFile> getProtocls() {
		return Collections.unmodifiableMap(protocls);
	}
	
	public ProtoclFile getProtocl(String name) {
		return protocls.get(name);
	}
	
}
