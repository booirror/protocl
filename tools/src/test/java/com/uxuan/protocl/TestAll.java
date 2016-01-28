package com.uxuan.protocl;

import com.uxuan.protocl.java.TestJavaFieldGen;
import com.uxuan.protocl.java.TestJavaStruct;
import com.uxuan.protocl.util.TestFileUtils;
import com.uxuan.protocl.util.TestStringUtils;

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
