package com.uxuan.protocl.util;


import org.junit.Assert;
import org.junit.Test;

import com.uxuan.protocl.util.StringUtils;

public class TestStringUtils {

	@Test
	public void testFirstCharUpCase() {
		Assert.assertEquals("Abcdefg", StringUtils.firstCharUpCase("abcdefg"));
	}
	
	@Test
	public void testFirstCharLowerCase() {
		Assert.assertEquals("abcdefg", StringUtils.firstCharLowerCase("Abcdefg"));
	}
	
	@Test
	public void testNextLine() {
		Assert.assertEquals("\n\n\n", StringUtils.nextLine(3, 0));
		Assert.assertEquals("\t\t", StringUtils.nextLine(0, 2));
		Assert.assertEquals("\n\n\n\t\t", StringUtils.nextLine(3, 2));
	}
}
