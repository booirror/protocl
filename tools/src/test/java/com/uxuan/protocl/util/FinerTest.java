package com.uxuan.protocl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月28日 下午9:48:36
 */
public class FinerTest {

	@Test
	public void testPackage() {
		Pattern pattern = Finer.PACKAGE.pattern;
		
		String line = "package com.xianxian.protocl;";
		Matcher matcher = pattern.matcher(line);
		Assert.assertEquals(true, matcher.find());
		
		line = " package com.xianxian.protocl;";
		line = "package com.xianxian.protocl ;";
		line = "package com.xianxian.protocl; ";
		line = "package com.xianxian.protocl ; ";
		line = " package com.xianxian.protocl ; ";
		line = "package com.xianxian.protocl";
		line = "com.xianxian.protocl;";
		line = "packae com.xianxian.protocl;";
		line = "packae com.xianxian.protocl;";
		
	}
	
}
