package com.swx.schedule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Scheduler {
	private HashSet<String> allSites = new HashSet<String>();
	private LinkedList<String> unvisitedSites = new LinkedList<String>();
	private int visitedCount = 0;

	private Byte[] obj = new Byte[0];

	public boolean isAdded(String url) {
		return allSites.contains(url);
	}

	public String featchUrl() {
		if (unvisitedSites.isEmpty()) {
			return null;
		} else {
			synchronized (obj) {
				visitedCount++;
				return unvisitedSites.poll();
			}
		}
	}

	public int getVisitedCount() {
		return visitedCount;
	}

	public void addUrl(String url) {
		if (url == null || url.isEmpty()) {
			return;
		}		
		synchronized (obj) {
			if (isAdded(url)) {
				return;
			}
			allSites.add(url);
			unvisitedSites.offer(url);
		}
	}

	public void addUrls(List<String> urls) {
		if (urls == null || urls.size() <= 0) {
			return;
		}
		synchronized (obj) {
			for(String url : urls) {
				if(!isAdded(url)) {
					allSites.add(url);
					unvisitedSites.add(url);
				}
			}
		}
	}

	public void addUrls(String... urls) {
		if (urls == null || urls.length <= 0) {
			return;
		}	
		synchronized (obj) {
			for (String url : urls) {
				if (!isAdded(url)) {					
					allSites.add(url);
					unvisitedSites.add(url);
				}
			}
			
		}
	}

}
