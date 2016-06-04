package com.swx.download;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author swx
 *
 */
public class PageDownload {

	public static final int DOWNLOAD_OK = 200;
	public static final int URL_ERROR = -1;
	public static final int IO_ERROR = -2;

	public interface PageDownloadCallback {
		void OnPageDownloadFinish(int code, String content);
	}

	public PageDownload() {

	}

	static final String URL_REGEX = "(https?://)?www\\.(.+?)";
	private Pattern p = Pattern.compile(URL_REGEX);
	private Matcher m = p.matcher(" ");

	private boolean isURLValidate(String url) {
		if (url == null || url.length() <= 0) {
			return false;
		}
		m.reset(url);
		return m.find();
	}

	public void downloadPage(String strUrl, PageDownloadCallback callback) {
		if (callback == null) {
			return;
		}
		if (!isURLValidate(strUrl)) {
			callback.OnPageDownloadFinish(URL_ERROR, null);
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
			callback.OnPageDownloadFinish(DOWNLOAD_OK, sb.toString());
			buffReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			callback.OnPageDownloadFinish(IO_ERROR, e.toString());
		}
	}
}
