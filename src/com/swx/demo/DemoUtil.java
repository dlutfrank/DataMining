package com.swx.demo;

import java.util.List;

import com.swx.data.ZhongChou;
import com.swx.util.Callback;
import com.swx.util.DataAnalyse;
import com.swx.util.PageDownload;
import com.swx.util.PageDownload.PageDownloadCallback;
import com.swx.util.Parse;

public class DemoUtil {
	static String URL_INPUT = "http://www.jb51.net/article/36797.htm";

	public static void main(String[] args) {
		String url = URL_INPUT;
		if (args.length > 2) {
			url = args[1];
		}

		PageDownload pageDownload = new PageDownload();
		pageDownload.downloadPage(ZhongChou.URL, new PageDownloadCallback() {
			@Override
			public void OnPageDownloadFinish(int code, String content) {
				if (code != PageDownload.DOWNLOAD_OK) {
					return;
				}
				DataAnalyse da = new DataAnalyse(content);
				// da.analyzeContent(new Callback<List<String>>() {
				//
				// @Override
				// public void onSuccess(List<String> result) {
				// System.out.println(result);
				// }
				//
				// @Override
				// public void onFaild(int code, String msg) {
				//
				// }
				// }, ZhongChou.REGEX2,0);
				da.analyzeContent(new Callback<List<ZhongChou>>() {

					@Override
					public void onSuccess(List<ZhongChou> result) {
						System.out.println(result);
					}

					@Override
					public void onFaild(int code, String msg) {

					}
				}, new Parse<ZhongChou>() {

					@Override
					public ZhongChou parse(String... strArray) {
						ZhongChou zc = null;
						if (strArray != null && strArray.length == 2) {
							if(strArray[0] != null) {
								zc = new ZhongChou();
								zc.url = strArray[0];
								zc.title = strArray[1];
							}
						}
						return zc;
					}
				}, ZhongChou.REGEX, 2,3);
			}
		});
	}
}
