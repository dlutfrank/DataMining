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

public class DemoUtil {
	public static final int PAGE_COUNT = 2;

	// http://www.zhongchou.com/browse/p3
	public static void main(String[] args) {
		crawl();
	}

	private static void crawl() {
		String[] urls = { "http://www.zhongchou.com/browse" };
		SiteProcess.Builder builder = new SiteProcess.Builder(urls).fileName("zhouchouDetail.txt")
				.targetUrl("^(http://)?www\\.zhongchou\\.com/browse(/p[1-9]\\d*)?$").pageCount(10);
		SiteProcess sp = builder.create();
		if (sp != null) {
			sp.start();
		}
	}

	private static void simpleCrawl() {
		String url = null;
		File file = new File("zhongchou.txt");
		BufferedWriter bf = null;
		try {
			bf = new BufferedWriter(new FileWriter(file));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<ZhongChouData> datas = null;
		for (int i = 1; i <= PAGE_COUNT; i++) {
			url = ZhongChouData.URL_PREFIX + i;
			try {
				datas = getZhongChouData(url);
				if (datas != null) {
					for (ZhongChouData zc : datas) {
						bf.write(zc.toString());
					}
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("page: " + i);
		}
		if (bf != null) {
			try {
				bf.flush();
				bf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static List<ZhongChouData> getZhongChouData(String url) throws IOException {
		Document doc = Jsoup.connect(url).timeout(3000).get();
		return ZhongChouData.parseData(doc);
	}
}
