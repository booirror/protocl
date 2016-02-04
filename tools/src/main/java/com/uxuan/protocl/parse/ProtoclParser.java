package com.uxuan.protocl.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.uxuan.protocl.MessageType;
import com.uxuan.protocl.module.Line;
import com.uxuan.protocl.module.Protocl;
import com.uxuan.protocl.module.ProtoclFile;
import com.uxuan.protocl.module.ProtoclMsg;
import com.uxuan.protocl.util.CloseAbles;
import com.uxuan.protocl.util.Files;


/**
 * 解析文档
 *
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月28日 下午9:20:57
 */
public class ProtoclParser {
	
	private Protocl protocl;
	
	public static void main(String[] args) throws IOException {
		ProtoclParser parser = new ProtoclParser();
		parser.loadMsg("./build");
		
		
		for (ProtoclFile pro : parser.protocl.getProtocls().values()) {
			System.out.println(pro);
		}
	}
	
	public void loadMsg(String dir) throws IOException {
		List<File> files = Files.getFiles(dir, "*.msg");
		protocl = new Protocl();
		
		for (File file : files) {
			ProtoclFile protoclFile = loadFile(file);
			protocl.addProtocl(protoclFile);
		}
	}
	
	public void parse()  {
		Map<String, ProtoclFile> msgMap = protocl.getProtocls();
		
		for (ProtoclFile protoFile : msgMap.values()) {
			Parser parser = new FileParser(protocl, protoFile);
			
			for (Line line : protoFile.getLineList()) {
				parser = parser.parse(line);
			}
		}
	}
	
	private ProtoclFile loadFile(File file) throws IOException {
		String fileName = file.getName();
		fileName = fileName.substring(0, fileName.indexOf('.'));
		
		List<Line> lines = readContent(file, fileName);
		ProtoclFile protoclFile = new ProtoclFile(protocl, fileName, lines);
		
		for (Line line : lines) {
			ProtoclMsg  msg = parserMessage(line, protoclFile);
			if (msg != null) {
				protoclFile.addMessage(msg);
			}
		}
		
		return protoclFile;
	}
	
	private List<Line> readContent(File file, String fileName) throws IOException {
		FileInputStream input = null;
		InputStreamReader inputReader = null;
		BufferedReader reader = null;
		try {
			input = new FileInputStream(file);
			inputReader = new InputStreamReader(input, "UTF-8");
			reader = new BufferedReader(inputReader);
			
			List<Line> dataList = new ArrayList<Line>(256);
			String line;
			for (int lineNum = 1; ((line = reader.readLine()) != null); lineNum ++) {
				dataList.add(new Line(fileName, lineNum, line));
			}
			
			return dataList;
		} finally {
			CloseAbles.close(reader, inputReader, input);
		}
	}
	
	private ProtoclMsg parserMessage(Line line, ProtoclFile protoclFile) {
		if (line.isEmpty()) {
			return null;
		}
		
		List<String> group = line.toGroup();
		String group0 = group.get(0);
		if (MessageType.ENUM.value().equals(group0)) {
			return new ProtoclMsg(protoclFile, MessageType.ENUM, group.get(1));
		}
		
		if (MessageType.MESSAGE.value().equals(group0)) {
			return new ProtoclMsg(protoclFile, MessageType.MESSAGE, group.get(1));
		}
		
		return null;
	}
}
