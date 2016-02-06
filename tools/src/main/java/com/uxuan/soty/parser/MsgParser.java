package com.uxuan.soty.parser;

import java.util.ArrayList;
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
		String defind = (index == -1) ? defindType : defindType.substring(0, index);
	
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
					.setGenericTypes(singtonTypes(defindType, line))
					.setName(groups.get(1))
					.build());
			return this;
		}
		
		// 无序数组
		if (DefindType.SET.isType(defind)) {
			soMessage.addAttribute(SoAttribute.newBuilder()
					.setType(soDefinition.setType(DefindType.SET).build())
					.setGenericTypes(singtonTypes(defindType, line))
					.setName(groups.get(1))
					.build());
			return this;
		}
		
		// 字典
		if (DefindType.MAP.isType(defind)) {
			soMessage.addAttribute(SoAttribute.newBuilder()
					.setType(soDefinition.setType(DefindType.MAP).build())
					.setGenericTypes(doubleTypes(defindType, line))
					.setName(groups.get(1))
					.build());
			return this;
		}
		
		
		SoDefinition definition = getGenericType(defindType, line);
		if (definition.getType() == DefindType.ENUM || 
			definition.getType() == DefindType.MESSAGE) {
			soMessage.addAttribute(SoAttribute.newBuilder()
					.setType(definition)
					.setName(groups.get(1))
					.build());
			return this;
		} 


		throw defindException(line);
	}
	
	private List<SoDefinition> singtonTypes(String defindType, SoLine line) {
		int begin = defindType.indexOf('<');
		int end = defindType.indexOf('>');
		if (begin <= 0 || end != (defindType.length() - 1) || defindType.indexOf(',') != -1) {
			defindException(line);
		}
		
		String content = defindType.substring(begin + 1, end);
		SoDefinition definition = getGenericType(content, line);
		
		return Arrays.asList(definition);
	}
	
	private List<SoDefinition> doubleTypes(String defindType, SoLine line) {
		int begin = defindType.indexOf('<');
		int split = defindType.indexOf(',');
		int end = defindType.indexOf('>');
		if (begin == -1 || end != (defindType.length() - 1) || split == -1 || begin >= end || split <= begin || split >= end) {
			defindException(line);
		}
		
		String content = defindType.substring(begin + 1, end);
		String[] generics = content.split(",");
		
		List<SoDefinition> defintions = new ArrayList<SoDefinition>(2);
		for (String generic : generics) {
			SoDefinition definition = getGenericType(generic, line);
			defintions.add(definition);
		}
		
		return defintions;
	}
	
	private SoDefinition getGenericType (String genericType, SoLine line) {
		if (genericType.indexOf('>') != -1 || genericType.indexOf('<') != -1) {
			defindException(line);
		}
		
		SoDefinition.Builder builder = SoDefinition.newBuilder().setTypeName(genericType);
		// boolean 类型
		if (DefindType.BOOL.isType(genericType)) {
			return builder.setType(DefindType.BOOL).build();
		}
		
		// 字节 类型
		if (DefindType.INT8.isType(genericType)) {
			return builder.setType(DefindType.INT8).build();
		}
		
		// 短整型
		if (DefindType.INT16.isType(genericType)) {
			return builder.setType(DefindType.INT16).build();
		}
		
		// 整型
		if (DefindType.INT32.isType(genericType)) {
			return builder.setType(DefindType.INT32).build();
		}
		
		// 长整型
		if (DefindType.INT64.isType(genericType)) {
			return builder.setType(DefindType.INT64).build();
		}
		
		// 单精度
		if (DefindType.FLOAT32.isType(genericType)) {
			return builder.setType(DefindType.FLOAT32).build();
		}
		
		// 双精度
		if (DefindType.FLOAT64.isType(genericType)) {
			return builder.setType(DefindType.FLOAT64).build();
		}
		
		//不支持array
		if (genericType.startsWith(DefindType.ARRAY.value)) {
			throw new RuntimeException("unsupport generic with type:array");
		}
		
		//不支持set
		if (genericType.startsWith(DefindType.SET.value)) {
			throw new RuntimeException("unsupport generic with type:set");
		}
		
		//不支持map
		if (genericType.startsWith(DefindType.MAP.value)) {
			throw new RuntimeException("unsupport generic with type:map");
		}
		
		int posIndex = genericType.indexOf('.');
		
		SoParseFile parseFile;
		if (posIndex == -1) {
			parseFile = soManager.getSoParseFile(soFile.getName());
		} else {
			String fileName = genericType.substring(0, posIndex);
			parseFile = soManager.getSoParseFile(fileName);
		}
		if (parseFile == null) {
			defindException(line);
		}
		
		SoMessage soMessage;
		if (posIndex == -1) {
			soMessage = parseFile.getMessage(genericType);
		} else {
			String fileName = genericType.substring(posIndex + 1, genericType.length());
			soMessage = parseFile.getMessage(fileName);
		}
		if (soMessage == null) {
			defindException(line);
		}
		
		if (soMessage.isType(ContentType.ENUM)) {
			return builder.setType(DefindType.ENUM).build();
		} 

		if (soMessage.isType(ContentType.MESSAGE)) {
			return builder.setType(DefindType.MESSAGE).build();
		} 

		throw defindException(line);
	}
}
