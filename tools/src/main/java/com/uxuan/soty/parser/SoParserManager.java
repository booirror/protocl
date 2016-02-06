package com.uxuan.soty.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.uxuan.soty.module.SoFile;
import com.uxuan.soty.module.SoMessage;
import com.uxuan.soty.parser.SoParserManager.SoParseFile;

public class SoParserManager {
	
	private Map<String, SoParseFile> soMap = new LinkedHashMap<String, SoParseFile>();

	
	public List<SoFile> parse(String dir) throws IOException {
		SoParser parser = new SoParser(this);
		List<SoParseFile> soParseFiles = parser.loadingSoFiles(dir);
		
		for (SoParseFile parseFile : soParseFiles) {
			soMap.put(parseFile.getName(), parseFile);
		}
		
		List<SoFile> soFileList = new ArrayList<SoFile>();
		for (SoParseFile parseFile : soMap.values()) {
			soFileList.add(parser.parse(parseFile));
		}
		
		return soFileList;
	}
	
	
	SoParseFile getSoParseFile(String fileName) {
		return soMap.get(fileName);
	}
	
	public static void main(String[] args) throws IOException {
		SoParserManager parseManager = new SoParserManager();
		
		List<SoFile> fileList = parseManager.parse("./build");
		
		
		for (SoFile soFile : fileList) {
			System.out.println("=======================" + soFile.getName() + "=======================");
			
			for (SoMessage msg : soFile.getMessages()) {
				System.out.println(msg);
			}
		}
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
