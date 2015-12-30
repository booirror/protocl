package com.uxuan.buffer.tools;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

public class TestMsgPat {

	@Test
	public void testPackage() throws IOException {
		int PREFIX = 0xF, SUFFIX = 0xC;
		short header = (short)((PREFIX << 8) | SUFFIX);
		Assert.assertEquals(0x0F0C, header);
	}

}
