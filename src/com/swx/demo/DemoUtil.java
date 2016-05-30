package com.swx.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.swx.data.ZhongChouData;

public class DemoUtil {
	public static final int PAGE_COUNT = 246;

	public static void main(String[] args) {
		String url = null;
		if (args.length > 2) {
			url = args[1];
		}
		File file = new File("zhongchou.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (int i = 1; i <= PAGE_COUNT; i++) {
			url = ZhongChouData.URL_PREFIX + i;
			getZhongChouData(url, fw);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("page: " + i);
		}
		if (fw != null) {
			try {
				fw.flush();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static void getZhongChouData(String url, FileWriter fw) {
		try {
			Document doc = Jsoup.connect(url).timeout(3000).get();
			List<ZhongChouData> datas = ZhongChouData.parseData(doc);			
			if (datas != null) {
				for (ZhongChouData zc : datas) {
					fw.write(zc.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
