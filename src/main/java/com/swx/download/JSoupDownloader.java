package com.swx.download;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.swx.common.Callback;
import com.swx.common.ErrorCode;

public class JSoupDownloader extends PageDownloader {
	public void downloadPage(String url, Callback<Document> callback) {
		if (!isUrlVaild(url)) {
			if (callback != null) {
				callback.onFaild(ErrorCode.PAGE_DOWNLOAD_URL_INVAILD, null);
			}
			return;
		}

		Document doc = null;
		try {
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
