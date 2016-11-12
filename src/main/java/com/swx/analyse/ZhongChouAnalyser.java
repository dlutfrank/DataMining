package com.swx.analyse;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.swx.data.ZhongChouData;
import com.swx.filter.UrlFilter;

public class ZhongChouAnalyser extends DocumentAnalyser {
	private List<UrlFilter> targetUrlFilter = null;
	private List<UrlFilter> assistUrlFilter = null;
	
	public static final String ITEM_LINK_TAG = "a";
	public static final String ITEM_LINK_URL_ATTR = "href";
	
	public ZhongChouAnalyser(List<UrlFilter> target, List<UrlFilter> assist) {
		targetUrlFilter = target;
		assistUrlFilter = assist;
	}

	@Override
	public String analyse(Document content,String  url) {
		if(targetUrlFilter == null) {
			return null;
		}
		for(UrlFilter filter: targetUrlFilter) {
			if(filter.isValidate(url)) {
				List<ZhongChouData>datas = ZhongChouData.parseData(content);
				StringBuilder sb = new StringBuilder();
				if(datas != null && !datas.isEmpty()) {
					for(ZhongChouData data : datas) {
						sb.append(data);
					}
					return sb.toString();
				} else {
					break;
				}				
			}
		}
		return null;
	}

	@Override
	public List<String> extractUrl(Document content) {
		List<String> urls = new ArrayList<String>();
		Elements eles = content.getElementsByTag(ITEM_LINK_TAG);
		String url = null;
		for(Element ele : eles) {
			url = ele.attr(ITEM_LINK_URL_ATTR);
			if(url != null && !url.isEmpty()) {
				for(UrlFilter filter: targetUrlFilter) {
					if(filter.isValidate(url)) {
						urls.add(url);
						break;
					}
				}
				for(UrlFilter filter: assistUrlFilter) {
					if(filter.isValidate(url)) {
						urls.add(url);
						break;
					}	
				}
			}
		}		
		return urls;
	}
}
