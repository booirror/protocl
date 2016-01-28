package com.uxuan.protocl;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.uxuan.protocl.GenService;
import com.uxuan.protocl.MsgBody;
import com.uxuan.protocl.MsgFile;

public class TestGenService {

	private GenService service;
	
	@Before
	public void setUP() throws Exception {
		service = new GenService();
		service.initialize("./bin/");
	}
	
	@Test
	public void testMsgBody() {
		List<MsgFile> msgFiles = service.msgFile();
		Assert.assertEquals(2, msgFiles.size());
		
		List<MsgBody> msgBodys = msgFiles.get(0).getMsgBodys();
		Assert.assertEquals(5, msgBodys.size());
		
		Assert.assertEquals("org/itas", msgFiles.get(0).getPackageName());
		Assert.assertEquals(true, msgFiles.get(0).getImports().contains("org/itas/TComm/Chirld"));
		
		Assert.assertEquals("Player", msgBodys.get(1).getMsgName(true));
		Assert.assertEquals(0, msgBodys.get(1).getMsgTypes().size());
	}
}
