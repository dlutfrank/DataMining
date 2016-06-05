package com.swx;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.swx.schedule.Scheduler;

public class SiteProcess {
	private static final int DEFAULT_THREAD_COUNT = 1;
	private int threadCount;
	private ExecutorService threadPool;
	private Scheduler urlScheduler;
	private String targetUrlRegex;
	private String assistUrlRegex;

	public static class Builder {
		private int count = DEFAULT_THREAD_COUNT;
		private String[] urls;
		private String targetRegex;
		private String assistRegex;

		public Builder(String... urls) {
			this.urls = urls;
		}

		public Builder targetUrl(String regex) {
			targetRegex = regex;
			return this;
		}

		public Builder assistUrl(String regex) {
			assistRegex = regex;
			return this;
		}

		public Builder threads(int n) {
			this.count = n;
			return this;
		}

		public SiteProcess create() {
			SiteProcess sp = new SiteProcess(count);
			sp.initSeeds(urls);
			sp.setTargetUrls(targetRegex);
			sp.setAssistUrls(assistRegex);
			return sp;
		}
	}

	public void start() {
		started = true;
		urlScheduler.addUrls(urlSeeds);
		lastProcessTime = System.currentTimeMillis();
		String url = null;
		while (started) {
			if ((url = urlScheduler.featchUrl()) != null) {
				lastProcessTime = System.currentTimeMillis();
				Runnable task = new SpiderTask(url);
				threadPool.submit(task);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				if (System.currentTimeMillis() - lastProcessTime > IDLE_DURATION) {
					stopInner();
				} else {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	public void stop() {

	}

	private void stopInner() {
		synchronized (obj) {
			started = false;
		}
	}

	private Byte[] obj = new Byte[0];

	private volatile boolean started = false;

	private long lastProcessTime = System.currentTimeMillis();

	private static final long IDLE_DURATION = 30 * 1000;

	private SiteProcess(int threads) {
		if (threads > 10 || threads < 1) {
			threadCount = DEFAULT_THREAD_COUNT;
		} else {
			threadCount = threads;
		}
		threadPool = Executors.newFixedThreadPool(threadCount);
	}

	private SiteProcess() {
		this(DEFAULT_THREAD_COUNT);
	}

	private void initSeeds(String... urls) {
		urlSeeds = urls;
	}

	private void setTargetUrls(String regex) {
		targetUrlRegex = regex;
	}

	private void setAssistUrls(String regex) {
		assistUrlRegex = regex;
	}

	private String[] urlSeeds;
}
