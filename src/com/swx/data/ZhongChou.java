package com.swx.data;

public class ZhongChou {
	public static final String URL = "http://www.zhongchou.com/browse/p245";
	public static final String REGEX = "<div class=\"ssCardICBox siteCardICBox( cgjs)?\">.+?<h3>\\s+<a href=\"(.+?)\""
			+ ".+?title=\"(.+?)\"";
	public static final String REGEX1 = "class=\"siteCardIC_p souSuo\">(.+?)</p>";
	public static final String REGEX2 = "class=\"siteCardFLabelBox siteIlB_box\">(.+?)</div>";
	public static final String REGEX3 = "class=\"siteCardFData\">\\s+<div class=\"ftDiv\">\\s+<p class=\"ftP\">(.\\d+)"
			+ ".+?class=\"scDiv\">\\s+<p class=\"ftP\">(\\d+).+?class=\"thDiv\">\\s+<p class=\"ftP\">(\\d+.)</p>";
	public static final String REGEX4 = "class=\"siteCardFLabelBox siteIlB_box\">\\s+(<a href.+?>(.+?)</a>\\s+)+</div>";
	public String url;
	public String title;
	
	public String toString() {
		return "url:  "+ url + "\ntitle: "+ title +"\n";
	}
}
