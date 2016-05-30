package com.swx.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author swx
 * @since 2016.5.27
 */
public class SiteUrls {

	private String strUrl;	

	/**
	 * @param args
	 */

	public SiteUrls(String url) {
		strUrl = url;
	}

	private String host;

	public void startSearch() {
		URL url;
		BufferedReader buffReader = null;
		try {
			url = new URL(strUrl);
			URLConnection conn = url.openConnection();
			host = url.getHost();
			buffReader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String regex = "<a href=\"((/\\w+)+\\.htm)\"";
			Pattern p = Pattern.compile(regex);
			String str = null;
			Matcher matcher = p.matcher(" ");
			String s = null;
			while ((str = buffReader.readLine()) != null) {
				matcher.reset(str);
				while (matcher.find()) {
					if ((s = matcher.group(1)) != null) {
						siteUrls.add(s);
					}
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (buffReader != null) {
				try {
					buffReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (siteUrls.size() != 0) {
			sb.append("site count: " + siteUrls.size());
			sb.append("\n");
			for (String site : siteUrls) {
				if (host != null) {
					sb.append(host);
				}
				sb.append(site);
				sb.append("\n");
			}
		} else {
			sb.append("no sites\n");
		}
		return sb.toString();
	}

	private List<String> siteUrls = new ArrayList<String>();

}
