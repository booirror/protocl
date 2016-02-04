package com.uxuan.protocl.util;


import org.junit.Assert;
import org.junit.Test;

public class TestStringUtils {

	@Test
	public void testFirstCharUpCase() {
		Assert.assertEquals("Abcdefg", Strings.firstCharUpCase("abcdefg"));
	}
	
	@Test
	public void testFirstCharLowerCase() {
		Assert.assertEquals("abcdefg", Strings.firstCharLowerCase("Abcdefg"));
	}
	
}
