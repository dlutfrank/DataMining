package com.swx.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.ansj.splitWord.analysis.ToAnalysis;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.swx.SiteProcess;
import com.swx.data.ZhongChouData;
import com.swx.data.ZhongChouDetail;

public class DemoUtil {
	public static int PAGE_COUNT = 10;

	public static void main(String[] args) {
		if (args.length > 0) {
			System.out.println(args[0]);
			PAGE_COUNT = Integer.valueOf(args[0]);
		}
		crawl();
		// test();
	}

	private static void test() {
		String[] urls = { "http://www.zhongchou.com/deal-show/id-528433" };
		SiteProcess.Builder builder = new SiteProcess.Builder(urls).fileName("zhongchouDetail.txt").targetUrl(urls)
				.assistUrl(null).pageCount(1);
		SiteProcess sp = builder.create();
		if (sp != null) {
			sp.start();
		}
	}

	private static void crawl() {
		long startTime = System.currentTimeMillis();
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
		long endTime = System.currentTimeMillis();
		System.out.println("time use: " + (endTime - startTime));
	}
}
