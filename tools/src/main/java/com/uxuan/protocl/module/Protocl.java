package com.uxuan.protocl.module;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月28日 下午9:21:51
 */
public class Protocl {

	/** 所有解析协议文件*/
	private final List<ProtoclFile> protocls;
	
	public Protocl() {
		protocls = new ArrayList<ProtoclFile>();
	}
	
	public void addProtocl(ProtoclFile protocl) {
		protocls.add(protocl);
	}

	public List<ProtoclFile> getProtocls() {
		return protocls;
	}
	
}
