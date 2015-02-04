package org.itas.tool.netbuf;

import java.io.IOException;

import org.junit.Test;

public class TestMsgPat {

	@Test
	public void testPackage() throws IOException {
		int PREFIX = 1, SUFFIX = 1;
		short header = (short)((PREFIX << 8) | SUFFIX);
		System.out.println(Integer.toHexString(header));
	}

}
