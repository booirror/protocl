package com.uxuan.protocl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.uxuan.soty.util.FilesTest;
import com.uxuan.soty.util.TestStringUtils;


@RunWith(Suite.class)
//指定运行器
@Suite.SuiteClasses({ 
	TestMsgPat.class, 
//	TestGenService.class, 
//	TestMsgField.class, 
//	TestJavaStruct.class,
//	TestJavaFieldGen.class,
	FilesTest.class,
	TestStringUtils.class,
	})
public class TestAll {
	
}
