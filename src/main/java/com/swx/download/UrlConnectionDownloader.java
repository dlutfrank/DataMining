package com.swx.download;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.swx.common.Callback;
import com.swx.common.ErrorCode;
import com.swx.filter.UrlFilter;

/**
 * @author swx
 *
 */
public class UrlConnectionDownloader implements IPageDownload<String> {
	private UrlFilter mUrlFilter = new UrlFilter();

	public void downloadPage(String strUrl, Callback<String> callback) {
		if (callback == null) {
			return;
		}
		if (!mUrlFilter.isValidate(strUrl)) {
			callback.onFaild(ErrorCode.PAGE_DOWNLOAD_URL_INVAILD, null);
			return;
		}
		BufferedReader buffReader = null;
		try {
			URL url = new URL(strUrl);
			URLConnection conn = url.openConnection();
			conn.connect();
			buffReader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = buffReader.readLine()) != null) {
				sb.append(line);
			}
			callback.onSuccess(sb.toString());
			buffReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			callback.onFaild(ErrorCode.IO_ERROR, e.toString());
		}
	}
}
