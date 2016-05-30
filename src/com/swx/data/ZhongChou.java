package com.swx.data;

/*
 <div class="ssCardICBox siteCardICBox cgjs">
 <div class="ssCardICText">
 <h3>
 <a href="http://www.zhongchou.com/deal-show/id-397546" class="siteCardICH3" title="反熵伐纣——首届CULT文化艺术展" target="_blank">反熵伐纣——首届CULT文化艺术展</a>
 </h3>
 <p class="siteCardIC_p souSuo">这将是你在魔都遇到的最酷的cult艺术展，我们只想充满共谋意味的邀请你加入一个清明有序的反熵世界。</p>
 </div>
 <div class="siteCardFBox cgjs">

 <div class="siteCardFLabelBox siteIlB_box">
 <a href="http://www.zhongchou.com/search/prid-310000" class="site_ALink siteIlB_item" target="_blank">上海</a>								
 <a href="http://www.zhongchou.com/search/lid-26" class="site_ALink siteIlB_item" target="_blank">展览</a>
 <a href="http://www.zhongchou.com/search/lid-65674" class="site_ALink siteIlB_item" target="_blank">cult</a>
 <a href="http://www.zhongchou.com/search/lid-5545" class="site_ALink siteIlB_item" target="_blank">艺术</a>
 </div>

 <div class="siteCardRatio">
 <div class="siteCardRatioInner" style="width: 100%;"></div>
 </div>
 <div class="siteCardFData">
 <div class="ftDiv">
 <p class="ftP">￥137506</p>
 <p class="scP">已筹款</p>
 </div>
 <div class="scDiv">
 <p class="ftP">1697</p>
 <p class="scP">支持数</p>
 </div>
 <div class="thDiv">
 <p class="ftP">138%</p>
 <p class="scP">筹款进度</p>
 </div>
 </div>
 </div>
 </div>
 */

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
