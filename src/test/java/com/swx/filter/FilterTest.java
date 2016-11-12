package com.swx.filter;

import org.junit.Assert;
import org.junit.Test;

public class FilterTest {
	
	@Test
	public void test_filter() {
		String[] targets = {"^http://www\\.zhongchou\\.com/deal-show/id-\\d+$"};
		String[] assist = {"^http://www\\.zhongchou\\.com/browse(/p[1-9]\\d*)?$",
				"^http://www\\.zhongchou\\.com/browse/id-\\d+$"};
		IFilter filter = new UrlFilter(assist[0]);
		IFilter targetFilter = new UrlFilter(targets[0]);
		IFilter assistFilter = new UrlFilter(assist[1]);
		
		Assert.assertTrue(filter.isValidate("http://www.zhongchou.com/browse"));
		Assert.assertTrue(filter.isValidate("http://www.zhongchou.com/browse/p1"));
		Assert.assertTrue(filter.isValidate("http://www.zhongchou.com/browse/p2"));
		Assert.assertFalse(filter.isValidate("http://www.zhongchou.com/browse/dwd"));
		Assert.assertFalse(filter.isValidate("www.zhongchou.com/browse"));
		Assert.assertFalse(filter.isValidate("www.zhongchou.com/browse/"));
		
		Assert.assertTrue(targetFilter.isValidate("http://www.zhouchou.com/deal-show/id-2"));
		Assert.assertTrue(targetFilter.isValidate("http://www.zhouchou.com/deal-show/id-24344"));
		Assert.assertFalse(targetFilter.isValidate("http://www.zhouchou.com/deal-show/id"));
		
		Assert.assertTrue(assistFilter.isValidate("http://www.zhouchou.com/id-12"));
		Assert.assertFalse(assistFilter.isValidate("http://www.zhouchou.com/id-"));
	}
}
