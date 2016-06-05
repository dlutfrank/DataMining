package com.swx.download;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;

import com.swx.common.Callback;
import com.swx.common.ErrorCode;

public class PageDownloader {
	public static final String COMMON_URL_REGEX = "(https?://)?www\\.(.+?)";
	protected Pattern urlP = null;
	protected Matcher urlMatcher = null;
	protected String urlRegex = null;
	
	protected IPageDownload IDownload = null;

	public PageDownloader() {
		this(null);
	}	

	public PageDownloader(String urlRegex) {
		if (urlRegex == null || urlRegex.isEmpty()) {
			this.urlRegex = COMMON_URL_REGEX;
		}
		urlP = Pattern.compile(this.urlRegex);
		urlMatcher = urlP.matcher("-*-");		
	}
	
	public boolean isUrlVaild(String url) {
		if (url != null && !url.isEmpty()) {
			urlMatcher.reset(url);
			return urlMatcher.matches();
		}
		return false;
	}
}
