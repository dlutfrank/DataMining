package com.swx.analyse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.swx.common.*;

/**
 * @author swx
 *
 */
public class DataAnalyse {
	private String content;
	public static final String SPLITE_TAG = "    ";

	public DataAnalyse(String content) {
		this.content = content;
	}

	public <T> void analyzeContent(Callback<List<T>> callback, StringsParse<T> iparse,
			String regex, int... indexs) {
		if (content == null || regex == null) {
			callback.onFaild(ErrorCode.PARAM_INVAILD, null);
			return;
		}
		if (indexs == null || indexs.length == 0) {
			indexs = new int[] { 0 };
		}
		List<T> results = new ArrayList<T>();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(content);
		String sa[] = new String[indexs.length];
		T tmp = null;
		while (m.find()) {
			Arrays.fill(sa, null);
			for (int i = 0; i < indexs.length; i++) {
				sa[i] = m.group(indexs[i]);
			}
			tmp = iparse.parse(sa);
			if (tmp != null) {
				results.add(tmp);
			}
		}
		if (callback != null) {
			callback.onSuccess(results);
		}
	}

	public void analyzeContent(Callback<List<String>> callback, String regex,
			int... indexs) {
		if (content == null || regex == null) {
			callback.onFaild(ErrorCode.PARAM_INVAILD, null);
			return;
		}
		if (indexs == null || indexs.length == 0) {
			indexs = new int[] { 0 };
		}
		List<String> results = new ArrayList<String>();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(content);
		StringBuilder sb = new StringBuilder();
		String s = null;
		while (m.find()) {
			sb.delete(0, sb.length());
			for (int i = 0; i < indexs.length; i++) {
				if ((s = m.group(indexs[i])) != null) {
					sb.append(s);
				} else {
					sb.append("NULL");
				}
				sb.append(SPLITE_TAG);
			}
			sb.append("\n");
			results.add(sb.toString());
		}
		if (callback != null) {
			callback.onSuccess(results);
		}
	}
}
