package com.uxuan.protocl.java;

import junit.framework.Assert;

import org.junit.Test;

import com.uxuan.protocl.GenService;
import com.uxuan.protocl.MsgFile;
import com.uxuan.protocl.generate.JavaFileGen;

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
