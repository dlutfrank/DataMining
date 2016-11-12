package com.swx.download;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.swx.common.Callback;
import com.swx.common.ErrorCode;
import com.swx.filter.UrlFilter;

public class JSoupDownloader implements IPageDownload<Document> {
	private UrlFilter mDefaultUrlFilter;

	public JSoupDownloader() {
		mDefaultUrlFilter = new UrlFilter();
	}

	public void downloadPage(String url, Callback<Document> callback) {
		if (!mDefaultUrlFilter.isValidate(url)) {
			if (callback != null) {
				callback.onFaild(ErrorCode.PAGE_DOWNLOAD_URL_INVAILD, null);
			}
			return;
		}

		Document doc = null;
		try {
			System.out.println("JSoupDownloader::downloadPage" + url);
			doc = Jsoup.connect(url).timeout(3000).get();
		} catch (IOException e) {
			e.printStackTrace();
			if (callback != null) {
				callback.onFaild(ErrorCode.IO_ERROR, e.toString());
			}
			return;
		}
		if (callback != null) {
			callback.onSuccess(doc);
		}

	}
}
