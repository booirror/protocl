package com.uxuan.buffer.util;

import java.io.File;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.uxuan.buffer.util.FileUtils;

public class TestFileUtils {

	@Test
	public void testGetFiles() {
		List<File> files = FileUtils.getFiles("./bin/", "*.msg");
		Assert.assertEquals(2, files.size());
		Assert.assertEquals("LoginInfo.msg", files.get(0).getName());
		Assert.assertEquals("TComm.msg", files.get(1).getName());

		files = FileUtils.getFiles("./bin/", "*.text");
		Assert.assertEquals(1, files.size());
		Assert.assertEquals("Test2.text", files.get(0).getName());

//		files = FileUtils.getFiles("./bin/", "*.*");
//		Assert.assertEquals(2, files.size());
	}
	
}
