package org.itas.buffer.tools;

import org.itas.buffer.tools.util.TestFileUtils;
import org.itas.buffer.tools.util.TestStringUtils;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
//指定运行器
@Suite.SuiteClasses({ 
	TestMsgPat.class, 
	TestMsgField.class, 
	TestFileUtils.class, 
	TestStringUtils.class, 
	})
public class TestAll {
	
}
