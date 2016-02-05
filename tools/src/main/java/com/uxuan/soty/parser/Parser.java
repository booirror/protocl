package com.uxuan.soty.parser;

import com.uxuan.soty.util.DefineException;

interface Parser {

	Parser parse(SoLine line);
	
	default DefineException defindException(SoLine line) {
		throw new DefineException("protocl:%s [line=%s], ", line.getFileName(), line.getLineNum());
	}
	
}
