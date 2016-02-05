package com.uxuan.soty.parser;

import java.util.Arrays;
import java.util.List;

import com.uxuan.soty.module.SoAttribute;
import com.uxuan.soty.module.SoDefinition;
import com.uxuan.soty.module.SoFile;
import com.uxuan.soty.module.SoMessage;
import com.uxuan.soty.parser.SoParserManager.SoParseFile;
import com.uxuan.soty.util.Constant.ContentType;
import com.uxuan.soty.util.Constant.DefindType;

final class MsgParser implements Parser {
	
	private SoParserManager soManager;

	private final Parser parent;
	
	private final SoMessage.Builder soMessage;
	
	private final SoFile.Builder soFile;
	
	public MsgParser(SoParserManager soManager, Parser parent, SoMessage.Builder soMessage, SoFile.Builder soFile) {
		this.parent = parent;
		this.soFile = soFile;
		this.soManager = soManager;
		this.soMessage = soMessage;
	}
	
	@Override
	public Parser parse(SoLine line) {
		if (line.isEmpty()) {
			return this;
		}
		
		List<String> groups = line.toGroup();
		
		String defindType = groups.get(0);
		if (ContentType.PACKAGE.isType(defindType)) {
			defindException(line);
		}
		
		if (ContentType.IMPORT.isType(defindType)) {
			defindException(line);
		}
		
		if (ContentType.ENUM.isType(defindType)) {
			defindException(line);
		}
		
		if (ContentType.MESSAGE.isType(defindType)) {
			defindException(line);
		}
		
		if ("{".equals(defindType)) {
			defindException(line);
		}
		
		if (groups.size() == 1 && "}".equals(defindType)) {
			return parent;
		}
		
		if (groups.size() != 3 || !";".equals(groups.get(2))) {
			defindException(line);
		}
		
		int index = defindType.indexOf('<');
		String defind = (index != -1) ? defindType : defindType.substring(0, index);
	
		SoDefinition.Builder soDefinition = SoDefinition.newBuilder().setTypeName(groups.get(0));
		
		// boolean 类型
		if (DefindType.BOOL.isType(defind)) {
			soMessage.addAttribute(SoAttribute.newBuilder()
					.setType(soDefinition.setType(DefindType.BOOL).build())
					.setName(groups.get(1))
					.build());
			return this;
		}
		
		// 字节 类型
		if (DefindType.INT8.isType(defindType)) {
			soMessage.addAttribute(SoAttribute.newBuilder()
					.setType(soDefinition.setType(DefindType.INT8).build())
					.setName(groups.get(1))
					.build());
			return this;
		}
		
		// 短整型
		if (DefindType.INT16.isType(defindType)) {
			soMessage.addAttribute(SoAttribute.newBuilder()
					.setType(soDefinition.setType(DefindType.INT16).build())
					.setName(groups.get(1))
					.build());
			return this;
		}
		
		// 整型
		if (DefindType.INT32.isType(defindType)) {
			soMessage.addAttribute(SoAttribute.newBuilder()
					.setType(soDefinition.setType(DefindType.INT32).build())
					.setName(groups.get(1))
					.build());
			return this;
		}
		
		// 长整型
		if (DefindType.INT64.isType(defind)) {
			soMessage.addAttribute(SoAttribute.newBuilder()
					.setType(soDefinition.setType(DefindType.INT64).build())
					.setName(groups.get(1))
					.build());
			return this;
		}
		
		// 单精度
		if (DefindType.FLOAT32.isType(defind)) {
			soMessage.addAttribute(SoAttribute.newBuilder()
					.setType(soDefinition.setType(DefindType.FLOAT32).build())
					.setName(groups.get(1))
					.build());
			return this;
		}
		
		// 双精度
		if (DefindType.FLOAT64.isType(defind)) {
			soMessage.addAttribute(SoAttribute.newBuilder()
					.setType(soDefinition.setType(DefindType.FLOAT64).build())
					.setName(groups.get(1))
					.build());
			return this;
		}

		// 双精度
		if (DefindType.STRING.isType(defind)) {
			soMessage.addAttribute(SoAttribute.newBuilder()
					.setType(soDefinition.setType(DefindType.STRING).build())
					.setName(groups.get(1))
					.build());
			return this;
		}
		
		// 有序数组 
		if (DefindType.ARRAY.isType(defind)) {
			soMessage.addAttribute(SoAttribute.newBuilder()
					.setType(soDefinition.setType(DefindType.ARRAY).build())
					.setName(groups.get(1))
					.build());
			return this;
		}
		
		// 无序数组
		if (DefindType.SET.isType(defind)) {
			soMessage.addAttribute(SoAttribute.newBuilder()
					.setType(soDefinition.setType(DefindType.SET).build())
					.setName(groups.get(1))
					.build());
			return this;
		}
		
		// 字典
		if (DefindType.MAP.isType(defind)) {
			soMessage.addAttribute(SoAttribute.newBuilder()
					.setType(soDefinition.setType(DefindType.MAP).build())
					.setName(groups.get(1))
					.build());
			return this;
		}
		
		
		String type = defindType.substring(0, index);
		AttrType attrType = new AttrType(type);
		
		String content = defindType.substring(index + 1, lastIndex);
		int indexMap = content.indexOf(',');
		if (indexMap == -1) {
			attrType.addGeneric(content);
		}
		
		String[] strs = content.split(",");
		if (strs.length != 2 || strs[0].length() == 0 || strs[1].length() == 0) {
			throwDefindException(line);
		}
		
		for (String str : strs) {
			attrType.addGeneric(str);
		}
//		if (msg.isType(MessageType.ENUM)) {
//			msg.addAttribute(ProtoclAttr.newBuilder()
//					 .setName(groups.get(0))
//					 .setMessage(msg)
//					 .setIndex(Integer.parseInt(groups.get(1)))
//					 .setType(instance.getAttribute(AttrType.ENUM))
//					 .build());
//		}  else if (msg.isType(MessageType.MESSAGE)) {
//			AttrType attrType = doAttrDefType(group0, line);
//			msg.addAttribute(new ProtoclAttr(msg, attrType, groups.get(1)));
//		} 
		
		int index = defindType.indexOf('<');
		int lastIndex = defindType.indexOf('>');
		//只有一个< 或者 >号
		if ((index == -1 && lastIndex != -1) || 
			(index != -1 && lastIndex == -1) || 
			(index != -1 && lastIndex != defindType.length() - 1)) {
			defindException(line);
		} 
		
		
		String defind = defindType;
		if (index != -1) {
			defind = defind.substring(0, index);
		}
		
//		
		return this;
	}
	
	private List<SoDefinition> singtonTypes(String defindType, SoLine line) {
		int begin = defindType.indexOf('<');
		int end = defindType.indexOf('>');
		if (begin <= 0 || end != (defindType.length() - 1) || defindType.indexOf(',') != -1) {
			defindException(line);
		}
		
		String content = defindType.substring(begin + 1, end);
		
		int posIndex = content.indexOf('.');
		
		SoParseFile parseFile;
		if (posIndex == -1) {
			parseFile = soManager.getSoParseFile(soFile.getName());
		} else {
			String fileName = content.substring(0, posIndex);
			parseFile = soManager.getSoParseFile(fileName);
		}
		
		if (parseFile == null) {
			defindException(line);
		}
		
		SoMessage soMessage;
		if (posIndex == -1) {
			soMessage = parseFile.getMessage(content);
		} else {
			String fileName = content.substring(posIndex + 1, content.length());
			soMessage = parseFile.getMessage(fileName);
		}
		
		DefindType type = null;
		if (soMessage.isType(ContentType.ENUM)) {
			type = DefindType.ENUM;
		} else  if (soMessage.isType(ContentType.MESSAGE)) {
			type = DefindType.MESSAGE;
		} else {
			defindException(line);
		}
		
		return Arrays.asList(SoDefinition.newBuilder()
				.setType(type)
				.setTypeName(soMessage.getName())
				.build());
	}
	
	private List<SoDefinition> doubleTypes(String defindType) {
		int begin = defindType.indexOf('<');
		int end = defindType.indexOf('>');
		if (begin <= 0 || end != (defindType.length() - 1) || defindType.indexOf(',') != -1) {
			defindException(line);
		}
		
		String content = defindType.substring(begin + 1, end);
		
		int posIndex = content.indexOf('.');
		
		SoParseFile parseFile;
		if (posIndex == -1) {
			parseFile = soManager.getSoParseFile(soFile.getName());
		} else {
			String fileName = content.substring(0, posIndex);
			parseFile = soManager.getSoParseFile(fileName);
		}
		
		if (parseFile == null) {
			defindException(line);
		}
		
		SoMessage soMessage;
		if (posIndex == -1) {
			soMessage = parseFile.getMessage(content);
		} else {
			String fileName = content.substring(posIndex + 1, content.length());
			soMessage = parseFile.getMessage(fileName);
		}
		
		DefindType type = null;
		if (soMessage.isType(ContentType.ENUM)) {
			type = DefindType.ENUM;
		} else  if (soMessage.isType(ContentType.MESSAGE)) {
			type = DefindType.MESSAGE;
		} else {
			defindException(line);
		}
		
		return Arrays.asList(SoDefinition.newBuilder()
				.setType(type)
				.setTypeName(soMessage.getName())
				.build());
	}
}
