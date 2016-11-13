package com.swx.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.swx.SiteProcess;
import com.swx.data.ZhongChouData;
import com.swx.data.ZhongChouDetail;

public class DemoUtil {
	public static final int PAGE_COUNT = 6000;

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		crawl();
		long endTime = System.currentTimeMillis();
		System.out.println("time use: " + (endTime - startTime));
	}

	private static void crawl() {
		String[] target = { "^http://www\\.zhongchou\\.com/deal-show/id-\\d+$" };
		String[] assist = { "^http://www\\.zhongchou\\.com/browse(/p[1-9]\\d*)?$",
				"^http://www\\.zhongchou\\.com/browse/id-\\d+$" };
		String[] urls = { "http://www.zhongchou.com/browse" };
		SiteProcess.Builder builder = new SiteProcess.Builder(urls).fileName("zhongchouDetail.txt").targetUrl(target)
				.assistUrl(assist).pageCount(PAGE_COUNT);
		SiteProcess sp = builder.create();
		if (sp != null) {
			sp.start();
		}
	}
}
