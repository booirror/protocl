package com.uxuan.buffer.tools;

import com.uxuan.buffer.tools.service.java.TestJavaFieldGen;
import com.uxuan.buffer.tools.service.java.TestJavaStruct;
import com.uxuan.buffer.tools.util.TestFileUtils;
import com.uxuan.buffer.tools.util.TestStringUtils;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
//指定运行器
@Suite.SuiteClasses({ 
	TestMsgPat.class, 
	TestGenService.class, 
	TestMsgField.class, 
	TestJavaStruct.class,
	TestJavaFieldGen.class,
	TestFileUtils.class,
	TestStringUtils.class,
	})
public class TestAll {
	
}
