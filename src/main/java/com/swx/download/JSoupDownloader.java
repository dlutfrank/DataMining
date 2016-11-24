package com.swx.download;

import java.io.IOException;

import org.jsoup.HttpStatusException;
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
			doc = Jsoup.connect(url).timeout(10000).get();
		} catch (HttpStatusException ex) {
			ex.printStackTrace();
			if (ex.getStatusCode() == 403) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (callback != null) {
				callback.onFaild(ErrorCode.IO_ERROR, ex.toString());
			}
			return;
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
