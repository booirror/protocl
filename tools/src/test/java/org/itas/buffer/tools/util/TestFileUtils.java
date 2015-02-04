package org.itas.buffer.tools.util;

import java.io.File;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class TestFileUtils {

	@Test
	public void testGetFiles() {
		List<File> files = FileUtils.getFiles("./bin/", ".msg");
		Assert.assertEquals(1, files.size());
		Assert.assertEquals("Test.msg", files.get(0).getName());

		files = FileUtils.getFiles("./bin/", ".text");
		Assert.assertEquals(1, files.size());
		Assert.assertEquals("Test2.text", files.get(0).getName());

		files = FileUtils.getFiles("./bin/", ".*");
		Assert.assertEquals(0, files.size());
	}
	
}
