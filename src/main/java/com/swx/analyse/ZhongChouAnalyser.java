package com.swx.analyse;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

import com.swx.filter.UrlFilter;

public class ZhongChouAnaylyser extends DocumentAnalyser {
	private List<UrlFilter> targetUrlFilter = null;
	private List<UrlFilter> assistUrlFilter = null;

	public ZhongChouAnaylyser() {

	}

	public void init(List<UrlFilter> target, List<UrlFilter> assist) {
		targetUrlFilter = target;
		assistUrlFilter = assist;
	}

	@Override
	public String analyse(Document content) {
		return null;
	}

	@Override
	public List<String> extractUrl(Document content) {
		List<String> urls = new ArrayList<String>();
		return urls;
	}
}
