package com.uxuan.soty.parser;

import org.junit.Assert;
import org.junit.Test;

public class LineTest {

	@Test
	public void contentTest() {
		SoLine line = new SoLine(null, 1, "//sdfsdfaasdfasdf");
		Assert.assertEquals("", line.getContent());
		
		line = new SoLine(null, 1, " //sdfsdfaasdfasdf");
		Assert.assertEquals("", line.getContent());
		
		line = new SoLine(null, 1, "asdf //sdfsdfaa//sdfasdf");
		Assert.assertEquals("asdf", line.getContent());
	}
	
}
