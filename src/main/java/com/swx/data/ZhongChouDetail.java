package com.swx.data;

import org.jsoup.nodes.Document;

public class ZhongChouDetail {
	public static final String ITEM_LINK_TAG = "a";
	public static final String ITEM_LINK_URL_ATTR = "href";

	private ZhongChouDetail() {

	}

	public ZhongChouDetail parseData(Document doc) {
		ZhongChouDetail result = null;
		if (doc == null) {
			return null;
		}

		return result;
	}

}
