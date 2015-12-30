package com.uxuan.buffer.tools.service.java;

import junit.framework.Assert;

import com.uxuan.buffer.tools.GenService;
import com.uxuan.buffer.tools.MsgFile;
import org.junit.Test;

public class TestJavaFile {
	
	private MsgFile msgfile;
	
	public void setUP() throws Exception {
		GenService service = new GenService();
		service.initialize("./bin/");
		
		msgfile = service.msgFile().get(0);
	}

	@Test
	public void testPrifexMethod() {
		JavaFileGen fileGen = new JavaFileGen(msgfile);
		
		String method = "\n\n\t@Override" +
						"\n\tpublic final byte PREFIX() {" +
						"\n\t\treturn Test.PREFIX;" +
						"\n\t}";
		
		Assert.assertEquals(method, fileGen.defPREFIXMethod());
	}
	
}
