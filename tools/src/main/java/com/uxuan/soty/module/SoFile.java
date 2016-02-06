package com.uxuan.soty.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 协议文件
 *
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月28日 下午9:10:06
 */
public final class SoFile {

	/** 消息文件名称*/
	private final String name;

/** 包名*/
	private final String pkg;
	
	/** 导入的包 */
	private final Set<String> imps;
	
	/** 消息文件包含的消息 */
	private final List<SoMessage> messages;
	
	
	private SoFile(String name, String pkg, Set<String> imps, List<SoMessage> messages) {
		this.name = name;
		this.pkg = pkg;
		this.imps = new HashSet<String>();
		this.messages = Collections.unmodifiableList(messages);
	}
	
	public String getName() {
		return name;
	}

	public String getPkg() {
		return pkg;
	}

	public Set<String> getImps() {
		return imps;
	}

	public List<SoMessage> getMessages() {
		return messages;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		
		if (pkg != null) {
			buf.append("package ").append(pkg).append(";");
		}
		
		buf.append("\n\n");
		for (String imp : imps) {
			buf.append("import ").append(imp).append(";");
		}
		
		
		for (SoMessage msg : messages){
			buf.append("\n\n");
			buf.append(msg);
		}
		
		return buf.toString();
	}
	
	public Builder toBuilder() {
		return newBuilder()
				.setName(name)
				.setPkg(pkg)
				.setImps(imps)
				.setMessages(messages);
	}
	
	public static Builder newBuilder() {
		return new Builder(); 
	}
	
	public static class Builder {
		
		private String pkg;
		private Set<String> imps;
		private String name;
		private List<SoMessage> messages;
		
		private Builder() {
			this.imps = new HashSet<String>();
			this.messages = new ArrayList<SoMessage>();
		}

		public String getPkg() {
			return pkg;
		}

		public Builder setPkg(String pkg) {
			if (this.pkg != null) 
				throw new RuntimeException("file only one package, old:" + this.pkg + ", given:" + pkg);

			this.pkg = pkg;
			return this;
		}

		public Set<String> getImps() {
			return imps;
		}

		public Builder setImps(Set<String> imps) {
			this.imps = imps;
			return this;
		}
		
		public Builder addImp(String imp) {
			this.imps.add(imp);
			return this;
		}

		public String getName() {
			return name;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public List<SoMessage> getMessages() {
			return messages;
		}

		public Builder setMessages(List<SoMessage> messages) {
			this.messages = messages;
			return this;
		}
		
		public Builder addMessages(SoMessage message) {
			this.messages.add(message);
			return this;
		}

		public SoFile build() {
			return new SoFile(name, pkg, imps, messages);
		}
	}
}
