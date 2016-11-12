package com.swx.filter;

import org.junit.Assert;
import org.junit.Test;

public class FilterTest {
	
	@Test
	public void test_filter() {
		IFilter filter = new UrlFilter("^(http://)?www\\.zhongchou\\.com/browse/?(p\\d*)?$");
		Assert.assertTrue(filter.isValidate("http://www.zhongchou.com/browse"));
		Assert.assertTrue(filter.isValidate("http://www.zhongchou.com/browse/p1"));
		Assert.assertTrue(filter.isValidate("http://www.zhongchou.com/browse/p2"));
		Assert.assertFalse(filter.isValidate("http://www.zhongchou.com/browse/dwd"));
		Assert.assertTrue(filter.isValidate("www.zhongchou.com/browse"));
		Assert.assertTrue(filter.isValidate("www.zhongchou.com/browse/"));
	}
}
