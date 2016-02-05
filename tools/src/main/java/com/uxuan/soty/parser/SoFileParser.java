package com.uxuan.soty.parser;

import java.util.List;

import com.uxuan.soty.module.SoFile;
import com.uxuan.soty.module.SoMessage;
import com.uxuan.soty.parser.SoParserManager.SoParseFile;
import com.uxuan.soty.util.Constant.ContentType;
import com.uxuan.soty.util.DefineException;


final class SoFileParser implements Parser {
	
	private SoParserManager soManager;
	
	private final SoFile.Builder soFile;
	
	public SoFileParser(SoParserManager soManager, SoFile.Builder soFile) {
		this.soFile = soFile;
		this.soManager = soManager;
	}

	@Override
	public Parser parse(SoLine line) {
		if (line.isEmpty()) {
			return this;
		}
		
		List<String> groups = line.toGroup();
		String defindType = groups.get(0);
		
		if (ContentType.PACKAGE.isType(defindType)) {
			return doPackage(line, groups);
		} 

		if (ContentType.IMPORT.isType(defindType)) {
			return doImport(line, groups);
		} 

		if (ContentType.ENUM.isType(defindType) || ContentType.MESSAGE.isType(defindType)) {
			return doMsg(line, groups);
		} 

		throw new DefineException("protocl:%s [line=%s], ", line.getFileName(), line.getLineNum());
	}
	
	private Parser doPackage(SoLine line, List<String> groups) {
		if (groups.size() != 3) {
			defindException(line);
		}
		
		if (!";".equals(groups.get(2))) {
			defindException(line);
		}
		
		soFile.setPkg(groups.get(1));
		
		return this;
	}
	
	private Parser doImport(SoLine line, List<String> groups) {
		if (groups.size() != 3) {
			defindException(line);
		}
		
		if (!";".equals(groups.get(2))) {
			defindException(line);
		}
		
		soFile.addImp(groups.get(1));
		
		return this;
	}
	
	private Parser doMsg(SoLine line, List<String> groups) {
		if (groups.size() != 3) {
			defindException(line);
		}
		
		if (!"{".equals(groups.get(2))) {
			
		}
		
		SoParseFile parseFile = soManager.getSoParseFile(soFile.getName());
		if (soFile == null) {
			defindException(line);
		}
		
		SoMessage soMessage = parseFile.getMessage(groups.get(1));
		if (soMessage == null) {
			defindException(line);
		}

		return new MsgParser(soManager, this, soMessage.toBuilder(), soFile);
	}

}
