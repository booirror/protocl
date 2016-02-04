package com.uxuan.protocl.parse;

import com.uxuan.protocl.module.Line;
import com.uxuan.protocl.util.DefineException;

interface Parser {

	Parser parse(Line line);
	
	default DefineException defindException(Line line) {
		throw new DefineException("protocl:%s [line=%s], ", line.getFileName(), line.getLineNum());
	}
	
}
