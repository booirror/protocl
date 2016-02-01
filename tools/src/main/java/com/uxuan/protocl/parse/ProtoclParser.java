package com.uxuan.protocl.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.uxuan.protocl.AttrType;
import com.uxuan.protocl.LanguageManager;
import com.uxuan.protocl.MessageType;
import com.uxuan.protocl.module.Protocl;
import com.uxuan.protocl.module.ProtoclAttr;
import com.uxuan.protocl.module.ProtoclFile;
import com.uxuan.protocl.module.ProtoclMsg;
import com.uxuan.protocl.util.CloseUtil;
import com.uxuan.protocl.util.DefineException;
import com.uxuan.protocl.util.Files;


/**
 * 解析文档
 *
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月28日 下午9:20:57
 */
public class ProtoclParser {
	
	public static void main(String[] args) throws IOException {
		ProtoclParser parser = new ProtoclParser();
		Protocl protocl =  parser.parse("./build");
		
		
		for (ProtoclFile pro : protocl.getProtocls()) {
			System.out.println(pro);
		}
	}
	
	public Protocl parse(String dir) throws IOException {
		List<File> files = Files.getFiles(dir, "*.msg");
		
		Protocl protocl = new Protocl();
		
		for (File file : files) {
			ProtoclFile protoclFile = parse(file);
			protocl.addProtocl(protoclFile);
		}
		
		return protocl;
	}
	
	private ProtoclFile parse(File file) throws IOException {
		String fileName = file.getName();
		fileName = fileName.substring(0, fileName.indexOf('.'));
		
		List<Line> lines = readContent(file, fileName);
		
		ProtoclFile protoclFile = new ProtoclFile(fileName);
		
		Parser parser = new ParseFile(protoclFile);
		for (Line line : lines) {
			parser = parser.parse(line);
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
			CloseUtil.close(reader, inputReader, input);
		}
	}
	
	private static DefineException throwDefindException(Line line) {
		throw new DefineException("protocl:%s [line=%s], ", line.getFileName(), line.getLineNum());
	}
	
	static class ParseFile implements Parser {
		
		private final ProtoclFile protoclFile;
		
		public ParseFile(ProtoclFile protoclFile) {
			this.protoclFile = protoclFile;
		}
	
		@Override
		public Parser parse(Line line) {
			if (line.isEmpty()) {
				return this;
			}
			
			List<String> groups = line.toGroup();
			String group0 = groups.get(0);
			if ("package".equals(group0)) {
				return doPackage(line, groups);
			} 

			if ("import".equals(group0)) {
				return doImport(line, groups);
			} 

			if ("enum".equals(group0)) {
				return doMsg(line, groups, MessageType.ENUM);
			} 

			if ("message".equals(group0)) {
				return doMsg(line, groups, MessageType.MESSAGE);
			} 
				
			throw new DefineException("protocl:%s [line=%s], ", line.getFileName(), line.getLineNum());
		}
		
		private Parser doPackage(Line line, List<String> groups) {
			if (groups.size() != 3) {
				throwDefindException(line);
			}
			
			if (!";".equals(groups.get(2))) {
				throwDefindException(line);
			}
			
			protoclFile.setPkg(groups.get(1));
			
			return this;
		}
		
		private Parser doImport(Line line, List<String> groups) {
			if (groups.size() != 3) {
				throwDefindException(line);
			}
			
			if (!";".equals(groups.get(2))) {
				throwDefindException(line);
			}
			
			protoclFile.addImport(groups.get(1));
			
			return this;
		}
		
		private Parser doMsg(Line line, List<String> groups, MessageType type) {
			if (groups.size() != 3) {
				throwDefindException(line);
			}
			
			if (!"{".equals(groups.get(2))) {
				throwDefindException(line);
			}
			
			ProtoclMsg protoclMsg = new ProtoclMsg(type, groups.get(1));
			protoclFile.addMessage(protoclMsg);
			
			return new ParseMsg(this, protoclMsg);
		}

	}
	
	static class ParseMsg implements Parser {
		
		private final Parser parent;
		
		private final ProtoclMsg msg;
		
		public ParseMsg(Parser parent, ProtoclMsg msg) {
			this.parent = parent;
			this.msg = msg;
		}
		
		@Override
		public Parser parse(Line line) {
			if (line.isEmpty()) {
				return this;
			}
			
			List<String> groups = line.toGroup();
			
			String group0 = groups.get(0);
			doCheck(group0, line);
			
			if (groups.size() == 3 && ";".equals(groups.get(2))) {
				LanguageManager instance = LanguageManager.getInstance();
				ProtoclAttr.Builder builder = ProtoclAttr.newBuilder().setName(groups.get(0));
				if (msg.isType(MessageType.ENUM)) {
					msg.addAttribute(ProtoclAttr.newBuilder()
							 .setName(groups.get(0))
							 .setMessage(msg)
							 .setIndex(Integer.parseInt(groups.get(1)))
							 .setType(instance.getAttribute(AttrType.ENUM))
							 .build());
				}  else if (msg.isType(MessageType.MESSAGE)) {
					AttrType attrType = doAttrDefType(group0, line);
					msg.addAttribute(new ProtoclAttr(msg, attrType, groups.get(1)));
				}
				
				return this;
			}
			
			if (groups.size() == 1 && "}".equals(group0)) {
				return parent;
			}
			
			throw throwDefindException(line);
		}
		
		private AttrType doAttrDefType(String group0, Line line) {
			
			int index = group0.indexOf('<');
			
			if (index == -1) {
				return new AttrType(group0);
			} 
			
			int lastIndex = group0.indexOf('>');
			if ((lastIndex + 1) != group0.length()) {
				throwDefindException(line);
			}
			
			String type = group0.substring(0, index);
			AttrType attrType = new AttrType(type);
			
			String content = group0.substring(index + 1, lastIndex);
			int indexMap = content.indexOf(',');
			if (indexMap == -1) {
				attrType.addGeneric(content);
				return attrType;
			}
			
			String[] strs = content.split(",");
			if (strs.length != 2 || strs[0].length() == 0 || strs[1].length() == 0) {
				throwDefindException(line);
			}
			
			for (String str : strs) {
				attrType.addGeneric(str);
			}
			
			return attrType;
		}
		
		private void doCheck(String group0, Line line) {
			if ("package".equals(group0) || 
				"import".equals(group0) || 
				"import".equals(group0) ||
				"message".equals(group0) ||
				"{".equals(group0)) {
				throwDefindException(line);
			}
		}

	}
	
}
