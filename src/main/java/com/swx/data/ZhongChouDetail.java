package com.swx.data;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ZhongChouDetail {
	public static final String ITEM_LINK_TAG = "a";
	public static final String ITEM_LINK_URL_ATTR = "href";
	public static final String ITEM_B = "b";
	public static final String ITEM_IMG = "img";
	public static final String ITEM_DATA_SRC = "data-src";

	// 头部
	public static final String ITEM_HEAD = "head";
	public static final String ITEM_NAME = "name";
	public static final String ITEM_TITLE = "title";
	public static final String ITEM_KEYWORDS = "keywords";
	public static final String ITEM_DESCRIPTION = "description";
	public static final String ITEM_CONTENT = "content";
	public static final String ITEM_PERSON_BOX = "faqipeeson";
	public static final String ITEM_PERSON = "txt2";

	public String title;
	public String keyword;
	public String desc;
	public String person;
	public String personPage;

	// 项目状态
	public static final String ITEM_PROJECT_STATE = "xqDetailBox";
	public static final String ITEM_PROJECT_HEART_BOX = "xqDetailLeft";
	public static final String ITEM_PROJECT_HEART = "heart-shaped";
	public static final String ITEM_PROJECT_DETAIL_RIGHT_BOX = "xqDetailRight";
	public static final String ITEM_DETAIL_VALUE_CLASS = "ftP";
	public static final String ITEM_TARGET_BOX = "xqRatioText";
	public static final String ITEM_DETAIL_CATEGORY_BOX = "jlxqTitleText";
	public static final String ITEM_DETAIL_GROUP_BOX = "gy";
	public static final String ITEM_DETAIL_GROUP_CLASS = "hoUdCLink";
	public static final String ITEM_PROJECT_ADDR = "addr";
	public static final String ITEM_PROJECT_LABEL = "label";

	public String heart;
	public String money;
	public String votes;
	public String progress;
	public String target;
	public String group;
	public List<String> addr = new LinkedList<String>();;
	public List<String> category = new LinkedList<String>();

	// 项目详情表格
	public static final String ITEM_PROJECT_DETAIL = "xqTabNav_ul";
	public static final String ITEM_PROJECT_DETAIL_ATTR = "data-scrollto";
	public static final String ITEM_PROJECT_UPATE = "zxjzBox";
	public static final String ITEM_PROJECT_COMMENT = "plOuterBox";
	public static final String ITEM_PROJECT_SUPPORT = "zczOuterBox";

	public String projectUpdate;
	public String projectComment;
	public String projectSupportRecord;

	// 项目描述区域
	public static final String ITEM_PROJECT_DESC_BOX = "xmxqBox";

	public List<String> images = new LinkedList<String>();
	public static final String ITEM_PROJECT_HAS_VIDEO = "media-time";
	public boolean hasVideo = false;

	// 分隔符
	public static final String ITEM_SPLIT_TAG = "||";
	public static final String CATEGORY_SPLITE_TAG = "    ";

	private ZhongChouDetail() {

	}

	public static String outline() {
		StringBuilder sb = new StringBuilder();
		sb.append("title");
		sb.append(ITEM_SPLIT_TAG);
		sb.append("keyword");
		sb.append(ITEM_SPLIT_TAG);
		sb.append("description");
		sb.append(ITEM_SPLIT_TAG);
		sb.append("person");
		sb.append(ITEM_SPLIT_TAG);
		sb.append("home_page");
		sb.append(ITEM_SPLIT_TAG);
		sb.append("project_update");
		sb.append(ITEM_SPLIT_TAG);
		sb.append("project_comment");
		sb.append(ITEM_SPLIT_TAG);
		sb.append("project_support");
		sb.append(ITEM_SPLIT_TAG);
		sb.append("label");
		sb.append(ITEM_SPLIT_TAG);
		sb.append("heart");
		sb.append(ITEM_SPLIT_TAG);
		sb.append("money");
		sb.append(ITEM_SPLIT_TAG);
		sb.append("vote");
		sb.append(ITEM_SPLIT_TAG);
		sb.append("progress");
		sb.append(ITEM_SPLIT_TAG);
		sb.append("target");
		sb.append(ITEM_SPLIT_TAG);
		sb.append("group");
		sb.append(ITEM_SPLIT_TAG);
		sb.append("addr");
		sb.append(ITEM_SPLIT_TAG);
		sb.append("image");
		sb.append("\n");
		return sb.toString();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(title);
		sb.append(ITEM_SPLIT_TAG);
		sb.append(keyword);
		sb.append(ITEM_SPLIT_TAG);
		sb.append(desc);
		sb.append(ITEM_SPLIT_TAG);
		sb.append(person);
		sb.append(ITEM_SPLIT_TAG);
		sb.append(personPage);
		sb.append(ITEM_SPLIT_TAG);
		sb.append(projectUpdate);
		sb.append(ITEM_SPLIT_TAG);
		sb.append(projectComment);
		sb.append(ITEM_SPLIT_TAG);
		sb.append(projectSupportRecord);
		sb.append(ITEM_SPLIT_TAG);
		for (String s : category) {
			sb.append(s);
			sb.append(CATEGORY_SPLITE_TAG);
		}
		sb.append(ITEM_SPLIT_TAG);
		sb.append(heart);
		sb.append(ITEM_SPLIT_TAG);
		sb.append(money);
		sb.append(ITEM_SPLIT_TAG);
		sb.append(votes);
		sb.append(ITEM_SPLIT_TAG);
		sb.append(progress);
		sb.append(ITEM_SPLIT_TAG);
		sb.append(target);
		sb.append(ITEM_SPLIT_TAG);
		sb.append(group);
		sb.append(ITEM_SPLIT_TAG);
		for (String s : addr) {
			sb.append(s);
			sb.append(CATEGORY_SPLITE_TAG);
		}
		sb.append(ITEM_SPLIT_TAG);
		for (String s : images) {
			sb.append(s);
			sb.append(CATEGORY_SPLITE_TAG);
		}
		sb.append(ITEM_SPLIT_TAG);
		sb.append(hasVideo);
		sb.append("\n");
		return sb.toString();
	}

	public static ZhongChouDetail parseData(Document doc) {
		ZhongChouDetail result = null;
		if (doc == null) {
			System.out.println("ZhongChouDetail::parseData, doc is null");
			return null;
		}
		Elements eles;
		Element eleGroup = null;
		Element ele = null;
		String attribute;
		String attr;
		result = new ZhongChouDetail();
		// 头部数据分析
		eleGroup = doc.getElementsByTag(ITEM_HEAD).first();
		if (eleGroup != null) {
			ele = doc.getElementsByTag(ITEM_TITLE).first();
			if (ele != null) {
				result.title = ele.text();
			}
			eles = eleGroup.getElementsByAttribute(ITEM_NAME);
			if (eles != null) {
				for (Element element : eles) {
					attribute = element.attr(ITEM_NAME);
					attr = element.attr(ITEM_CONTENT);
					if (attribute.equals(ITEM_KEYWORDS)) {
						result.keyword = attr;
					} else if (attribute.equals(ITEM_DESCRIPTION)) {
						result.desc = attr;
					}
				}
			}
		}

		eleGroup = doc.getElementsByClass(ITEM_PERSON_BOX).first();
		if (eleGroup != null) {
			ele = eleGroup.getElementsByClass(ITEM_PERSON).first();
			if (ele != null) {
				ele = ele.getElementsByTag(ITEM_LINK_TAG).first();
				if (ele != null) {
					result.person = ele.text();
					result.personPage = ele.attr(ITEM_LINK_URL_ATTR);
				}
			}
		}
		// 项目状态数据
		eleGroup = doc.getElementsByClass(ITEM_PROJECT_STATE).first();
		if (eleGroup != null) {
			eles = eleGroup.getElementsByClass(ITEM_DETAIL_VALUE_CLASS);
			if (eles != null && eles.size() >= 3) {
				result.votes = eles.get(0).text();
				result.money = eles.get(1).text();
				result.progress = eles.get(2).text();
			}
			ele = eleGroup.getElementsByClass(ITEM_PROJECT_HEART).first();
			if (ele != null) {
				result.heart = ele.text();
			}
			ele = eleGroup.getElementsByClass(ITEM_TARGET_BOX).first();
			if (ele != null) {
				Element el = ele.getElementsByTag(ITEM_B).first();
				if (el != null) {
					result.target = el.text();
				}
			}
			ele = eleGroup.getElementsByClass(ITEM_PROJECT_DETAIL_RIGHT_BOX).first();
			if (ele != null) {
				Element el = null;
				Element child = null;
				ele = ele.getElementsByClass(ITEM_DETAIL_CATEGORY_BOX).first();
				if (ele != null) {
					el = ele.getElementsByClass(ITEM_DETAIL_GROUP_BOX).first();
					if (el != null) {
						child = el.child(0);
						if (child != null) {
							result.group = child.text();
						}
					}
					el = ele.getElementsByClass(ITEM_PROJECT_ADDR).first();
					if (el != null) {
						eles = el.getElementsByTag(ITEM_LINK_TAG);
						if (eles != null) {
							String name = null;
							for (Element element : eles) {
								name = element.text();
								if (name != null && !name.isEmpty()) {
									result.addr.add(name);
								}
							}
						}
					}
					el = ele.getElementsByClass(ITEM_PROJECT_LABEL).first();
					if (el != null) {
						eles = el.getElementsByTag(ITEM_LINK_TAG);
						if (eles != null) {
							String name = null;
							for (Element e : eles) {
								name = e.text();
								if (name != null && !name.isEmpty()) {
									result.category.add(name);
								}
							}
						}
					}
				}
			}
		}

		// 项目详情表格数据解析
		eleGroup = doc.getElementById(ITEM_PROJECT_DETAIL);
		if (eleGroup != null) {
			eles = eleGroup.getElementsByAttribute(ITEM_PROJECT_DETAIL_ATTR);
			if (eles != null) {

				for (Element element : eles) {
					attribute = element.attr(ITEM_PROJECT_DETAIL_ATTR);
					ele = element.getElementsByTag(ITEM_B).first();
					if (ele != null) {
						if (attribute.equals(ITEM_PROJECT_UPATE)) {
							result.projectUpdate = ele.text();
						} else if (attribute.equals(ITEM_PROJECT_COMMENT)) {
							result.projectComment = ele.text();
						} else if (attribute.equals(ITEM_PROJECT_SUPPORT)) {
							result.projectSupportRecord = ele.text();
						}
					}

				}
			}
		}
		// 项目描述数据解析
		eleGroup = doc.getElementById(ITEM_PROJECT_DESC_BOX);
		if (eleGroup != null) {
			eles = eleGroup.getElementsByTag(ITEM_IMG);
			if (eles != null) {
				for (Element el : eles) {
					attribute = el.attr(ITEM_DATA_SRC);
					if (attribute != null && !attribute.isEmpty()) {
						result.images.add(attribute);
					}
				}
			}
		}

		eleGroup = doc.getElementsByClass(ITEM_PROJECT_HAS_VIDEO).first();
		if (eleGroup != null) {
			result.hasVideo = true;
		}
		return result;
	}

}
