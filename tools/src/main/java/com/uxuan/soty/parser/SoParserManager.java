package com.uxuan.soty.parser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.uxuan.soty.module.SoMessage;

public class SoParserManager {
	
	private Map<String, SoParseFile> soMap = new LinkedHashMap<String, SoParseFile>();

	
	public void loadFile(String dir) {
		
	}
	
	public void doParse() {
		
	}
	
	SoParseFile getSoParseFile(String fileName) {
		return soMap.get(fileName);
	}
	
	static class SoParseFile {
		
		/** so文件名称*/
		private final String name;
		
		/** 文件包含的消息*/
		private final Map<String, SoMessage> messages;
		
		/** 解析行*/
		private final List<SoLine> lines;
		
		public SoParseFile(String name) {
			this.name = name;
			this.messages = new LinkedHashMap<String, SoMessage>();
			this.lines = new ArrayList<SoLine>();
		}

		public String getName() {
			return name;
		}

		public SoMessage getMessage(String name) {
			return messages.get(name);
		}
		
		public void addMessage(SoMessage msg) {
			if(messages.put(msg.getName(), msg) != null) {
				throw  new RuntimeException("duplicate " + msg.getName() + " in file:" + name);
			}
		}

		public List<SoLine> getLines() {
			return lines;
		}
		
		public SoLine addLine(SoLine line) {
			this.lines.add(line);
			return line;
		}
	}
}
