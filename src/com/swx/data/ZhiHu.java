package com.swx.data;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * <a class="question_link" 
 * href="/question/38146816/answer/76789501" 
 * data-id="7465914">世界各地的「爱乐乐团」其乐团运作现况是如何？为何有以国家或城市做为命名的？
 * </a>
 */
public class ZhiHu {
	public static final String RECOMMEND_URL = "https://www.zhihu.com/explore/recommendations";
	public static final String URL_HOST = "https://www.zhihu.com";
	public static final String RQR = "\"question_link\"\\shref=\"(.+?)\".+?>(.+?)</a>";
	public String questionUrl;
	public String question;
	public List<String> answers;

	private static final String QUESTION_REGEX = "    ";
	private static Pattern p = Pattern.compile(QUESTION_REGEX);
	private static Matcher m = p.matcher("+++");
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[question: ");
		sb.append(question);
		sb.append("\n");
		sb.append(questionUrl);
		sb.append("\n");		
		return sb.toString();
	}

	public static ZhiHu Parse(String str) {
		ZhiHu zhihu = null;
		String[] ps = null;
		if (str != null && !str.isEmpty()) {
			ps = p.split(str);
			if (ps == null || ps.length < 2) {
				return zhihu;
			}
			if ("NULL".equals(ps[0]) || "NULL".equals(ps[1])) {
				return zhihu;
			}
			zhihu = new ZhiHu();
			zhihu.questionUrl = URL_HOST + ps[0];
			zhihu.question = ps[1];
		}
		return zhihu;
	}
}
