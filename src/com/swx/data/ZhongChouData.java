package com.swx.data;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ZhongChouData {
	public static final String URL_PREFIX = "http://www.zhongchou.com/browse/p";
	public String url;
	public String title;
	public String desc;
	public List<String> category;
	public String money;
	public String votes;
	public String progress;

	public static final String ITEM_TAG_CLASS = "ssCardICBox siteCardICBox";
	public static final String ITEM_TAG_CLASS_1 = "ssCardICBox siteCardICBox cgjs";
	public static final String ITEM_LINK_TAG = "a";
	public static final String ITEM_LINK_URL_ATTR = "href";
	public static final String ITEM_DESC_CLASS = "siteCardIC_p souSuo";
	public static final String ITEM_CATEGORY_CLASS = "siteCardFLabelBox siteIlB_box";
	public static final String ITEM_MONEY_CLASS = "ftDiv";
	public static final String ITEM_VOTE_CLASS = "scDiv";
	public static final String ITEM_PROGRESS_CLASS = "thDiv";
	public static final String ITEM_DETAIL_VALUE_CLASS = "ftP";

	public static List<ZhongChouData> parseData(Document doc) {
		List<ZhongChouData> zcdl = new ArrayList<ZhongChouData>();
		Elements els = doc.getElementsByTag(ITEM_TAG_CLASS);
		ZhongChouData zcd = null;
		for (Element el : els) {
			zcd = parseData(el);
			if (zcd != null) {
				zcdl.add(zcd);
			}
		}
		els = doc.getElementsByTag(ITEM_TAG_CLASS_1);
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

		return zcd;
	}
}
