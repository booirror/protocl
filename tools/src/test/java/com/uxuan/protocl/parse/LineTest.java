package com.uxuan.protocl.parse;

import org.junit.Assert;
import org.junit.Test;

public class LineTest {

	@Test
	public void contentTest() {
		Line line = new Line(null, 1, "//sdfsdfaasdfasdf");
		Assert.assertEquals("", line.getContent());
		
		line = new Line(null, 1, " //sdfsdfaasdfasdf");
		Assert.assertEquals("", line.getContent());
		
		line = new Line(null, 1, "asdf //sdfsdfaa//sdfasdf");
		Assert.assertEquals("asdf", line.getContent());
	}
	
}
