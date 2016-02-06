package com.uxuan.soty.util;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class FilesTest {

	@Test
	public void testGetFiles() {
		List<File> files = Files.getFiles("./src", "*.msg");
		Assert.assertEquals(2, files.size());
		Assert.assertEquals("Family.msg", files.get(1).getName());
		Assert.assertEquals("Comm.msg", files.get(0).getName());
	}
	
}
