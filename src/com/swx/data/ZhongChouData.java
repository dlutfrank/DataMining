package com.swx.data;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

public class ZhongChouData {
	public static final String URL_PREFIX = "http://www.zhongchou.com/browse/p";
	public String url;
	public String title;
	public String desc;
	public List<String> category;
	public String money;
	public String votes;
	public String progress;

	public static final String ITEM_TAG_CLASS = "ssCardICBox";// siteCardICBox
																// cgjs
	public static final String ITEM_URL_CLASS = "siteCardICH3";
	public static final String ITEM_LINK_TAG = "a";
	public static final String ITEM_LINK_URL_ATTR = "href";

	public static final String ITEM_DESC_CLASS = "siteCardIC_p";// souSuo
	public static final String ITEM_CATEGORY_CLASS = "siteCardFLabelBox"; // siteIlB_box
	public static final String ITEM_MONEY_CLASS = "ftDiv";
	public static final String ITEM_VOTE_CLASS = "scDiv";
	public static final String ITEM_PROGRESS_CLASS = "thDiv";
	public static final String ITEM_DETAIL_VALUE_CLASS = "ftP";

	public static List<ZhongChouData> parseData(Document doc) {
		List<ZhongChouData> zcdl = new ArrayList<ZhongChouData>();
		Elements els = doc.getElementsByClass(ITEM_TAG_CLASS);
		ZhongChouData zcd = null;
		for (Element el : els) {
			zcd = parseData(el);
			if (zcd != null) {
				zcdl.add(zcd);
			}
		}
		return zcdl;
	}

	public static ZhongChouData parseData(Element el) {
		if (el == null) {
			return null;
		}
		ZhongChouData zcd = new ZhongChouData();
		Element ele = null;
		ele = el.getElementsByClass(ITEM_URL_CLASS).first();
		if (ele != null) {
			zcd.url = ele.attr(ITEM_LINK_URL_ATTR);
			zcd.title = ele.text();
		}

		ele = el.getElementsByClass(ITEM_DESC_CLASS).first();
		if (ele != null) {
			zcd.desc = ele.text();
		}

		Elements eles = null;
		ele = el.getElementsByClass(ITEM_CATEGORY_CLASS).first();
		if (ele != null) {
			eles = ele.getElementsByTag(ITEM_LINK_TAG);
			if (eles != null) {
				zcd.category = new ArrayList<String>();
				for (Element etmp : eles) {
					zcd.category.add(etmp.text());
				}
			}
		}

		Element et = null;
		ele = el.getElementsByClass(ITEM_MONEY_CLASS).first();
		if (ele != null) {
			et = ele.getElementsByClass(ITEM_DETAIL_VALUE_CLASS).first();
			if (et != null) {
				zcd.money = et.text();
			}
		}

		ele = el.getElementsByClass(ITEM_VOTE_CLASS).first();
		if (ele != null) {
			et = ele.getElementsByClass(ITEM_DETAIL_VALUE_CLASS).first();
			if (et != null) {
				zcd.votes = et.text();
			}
		}

		ele = el.getElementsByClass(ITEM_PROGRESS_CLASS).first();
		if (ele != null) {
			et = ele.getElementsByClass(ITEM_DETAIL_VALUE_CLASS).first();
			if (et != null) {
				zcd.progress = et.text();
			}
		}
		return zcd;
	}
}
