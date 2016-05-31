package com.swx;

import java.util.LinkedList;

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
	}
}
