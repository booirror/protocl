package com.uxuan.soty.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.uxuan.soty.module.SoFile;
import com.uxuan.soty.module.SoMessage;
import com.uxuan.soty.parser.SoParserManager.SoParseFile;
import com.uxuan.soty.util.Constant.ContentType;
import com.uxuan.soty.util.Files;


/**
 * 解析文档
 *
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月28日 下午9:20:57
 */
final class SoParser {
	
	private SoParserManager soManager;
	
	public SoParser(SoParserManager soManager) {
		this.soManager = soManager;
	}
	
	
	public List<SoParseFile> loadingSoFiles(String dir) throws IOException {
		List<SoParseFile> soParseList = new ArrayList<SoParseFile>();

		List<File> files = Files.getFiles(dir, "*.msg");
		for (File file : files) {
			soParseList.add(loadingContent(file));
		}
		
		return soParseList;
	}
	
	public SoFile parse(SoParseFile soParseFile)  {
		SoFile.Builder soFile = SoFile.newBuilder();
		soFile.setName(soParseFile.getName());
		
		Parser parser = new SoFileParser(soManager, soFile);
		for (SoLine soLine : soParseFile.getLines()) {
			parser = parser.parse(soLine);
		}
		
		return soFile.build();
	}
	
	private SoParseFile loadingContent(File file) throws IOException {
		String fileName = file.getName();
		fileName = fileName.substring(0, fileName.indexOf('.'));
		
		SoParseFile soFile = new SoParseFile(fileName);
		
		try (FileInputStream input = new FileInputStream(file);
			InputStreamReader inputReader = new InputStreamReader(input, "UTF-8");
			BufferedReader reader = new BufferedReader(inputReader)) {
			
			int index = 0;
			String line;
			while ((line = reader.readLine()) != null) {
				index ++;
				SoLine lin = soFile.addLine(new SoLine(fileName, index, line));
				
				//空行，下一循环
				if (lin.isEmpty()) {
					continue;
				}
				
				List<String> group = lin.toGroup();
				String defindType = group.get(0);
				
				//定义类型为枚举
				if (ContentType.ENUM.isType(defindType)) {
					soFile.addMessage(SoMessage.newBuilder()
							.setType(ContentType.ENUM)
							.setName(group.get(1))
							.build());
					continue;
				}
				
				//定义类型为消息
				if (ContentType.MESSAGE.isType(defindType)) {
					soFile.addMessage(SoMessage.newBuilder()
							.setType(ContentType.MESSAGE)
							.setName(group.get(1))
							.build());
					continue;
				}
			}
		} 
		
		return soFile;
	}
		
	
}
