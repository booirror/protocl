package com.uxuan.protocl.parse;

import java.util.List;

import com.uxuan.protocl.MessageType;
import com.uxuan.protocl.module.Line;
import com.uxuan.protocl.module.Protocl;
import com.uxuan.protocl.module.ProtoclFile;
import com.uxuan.protocl.module.ProtoclMsg;
import com.uxuan.protocl.util.DefineException;

final class FileParser implements Parser {
	
	
	private final ProtoclFile file;
	
	public FileParser(Protocl protocl, ProtoclFile file) {
		this.file = file;
	}

	@Override
	public Parser parse(Line line) {
		if (line.isEmpty()) {
			return this;
		}
		
		List<String> groups = line.toGroup();
		
		String group0 = groups.get(0);
		if (MessageType.PACKAGE.isType(group0)) {
			return doPackage(line, groups);
		} 

		if (MessageType.IMPORT.isType(group0)) {
			return doImport(line, groups);
		} 

		if (MessageType.ENUM.isType(group0) || MessageType.MESSAGE.isType(group0)) {
			return doMsg(line, groups);
		} 

		throw new DefineException("protocl:%s [line=%s], ", line.getFileName(), line.getLineNum());
	}
	
	private Parser doPackage(Line line, List<String> groups) {
		if (groups.size() != 3) {
			defindException(line);
		}
		
		if (!";".equals(groups.get(2))) {
			defindException(line);
		}
		
		file.setPkg(groups.get(1));
		
		return this;
	}
	
	private Parser doImport(Line line, List<String> groups) {
		if (groups.size() != 3) {
			defindException(line);
		}
		
		if (!";".equals(groups.get(2))) {
			defindException(line);
		}
		
		file.addImport(groups.get(1));
		
		return this;
	}
	
	private Parser doMsg(Line line, List<String> groups) {
		if (groups.size() != 3) {
			defindException(line);
		}
		
		if (!"{".equals(groups.get(2))) {
			
		}
		
		ProtoclMsg protoclMsg = file.getMsg(groups.get(1));
		if (protoclMsg == null) {
			defindException(line);
		}
		
		return new MsgParser(this, protoclMsg);
	}

}
