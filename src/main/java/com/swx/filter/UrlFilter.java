package com.swx.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlFilter implements IFilter<String> {
	private String mRegex;
	private Pattern mPattern;
	private Matcher mMatcher;

	public static final String COMMON_URL_REGEX = "(https?://)?www\\.(.+?)";

	public UrlFilter() {
		this(COMMON_URL_REGEX);
	}

	public UrlFilter(String regex) {
		if (regex == null || regex.isEmpty()) {
			regex = COMMON_URL_REGEX;
		}
		mRegex = regex;
		mPattern = Pattern.compile(mRegex);
		mMatcher = mPattern.matcher(" ");
	}

	@Override
	public boolean isValidate(String content) {
		if (content == null || content.isEmpty()) {
			return false;
		}
		mMatcher.reset(content);
		return mMatcher.matches();
	}

}
