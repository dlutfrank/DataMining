package com.swx;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Scheduler {
	private LinkedList<String> visitedSites = new LinkedList<String>();
	private LinkedList<String> unvisitedSites = new LinkedList<String>();

	public boolean isVisited(String url) {
		return visitedSites.contains(url);
	}

	public void addUrl(String url) {
		if (url == null || url.isEmpty()) {
			return;
		}
		if (isVisited(url)) {
			return;
		}
		unvisitedSites.offer(url);
		try {
			Document doc = Jsoup.connect("http://www.baidu.com").timeout(3000)
					.get();
//			Element ele = doc.getElementsByTag(tagName);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
