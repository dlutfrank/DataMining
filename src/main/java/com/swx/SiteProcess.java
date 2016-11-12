package com.swx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.swx.analyse.DocumentAnalyser;
import com.swx.analyse.ZhongChouAnalyser;
import com.swx.download.JSoupDownloader;
import com.swx.filter.UrlFilter;
import com.swx.output.FileOutputManager;
import com.swx.schedule.Scheduler;

public class SiteProcess {
	private static final int DEFAULT_THREAD_COUNT = 1;
	private static final long DEFAULT_PAGE_LIMITED = 10;
	private int threadCount;
	private ExecutorService threadPool;
	private Scheduler urlScheduler = new Scheduler() ;
	private JSoupDownloader downloader = new JSoupDownloader();
	private DocumentAnalyser analyser = null;
	private FileOutputManager output = new FileOutputManager();
	private long pageLimited = 1000l;
	private String fileName;

	public static class Builder {
		private int count = DEFAULT_THREAD_COUNT;
		private String[] urls;
		private String[] targetRegex;
		private String[] assistRegex;
		private long pageLimited = DEFAULT_PAGE_LIMITED;
		private String fileName;

		public Builder(String... urls) {
			this.urls = urls;
		}

		public Builder targetUrl(String... regex) {
			targetRegex = regex;
			return this;
		}

		public Builder assistUrl(String... regex) {
			assistRegex = regex;
			return this;
		}

		public Builder threads(int n) {
			this.count = n;
			return this;
		}
		
		public Builder pageCount(long pageCount) {
			if(pageCount> 0) {
				this.pageLimited = pageCount;	
			}			
			return this;
		}
		
		public Builder fileName(String fileName) {
			if(fileName != null && !fileName.isEmpty()) {
				this.fileName = fileName;
			}
			return this;
		}

		public SiteProcess create() {
			SiteProcess sp = new SiteProcess(count);
			sp.initSeeds(urls);
			sp.setPageCount(pageLimited);	
			sp.setOutputFile(fileName);
			List<UrlFilter> targetFilter = new ArrayList<UrlFilter>();
			List<UrlFilter> assistFilter = new ArrayList<UrlFilter>();
			if (targetRegex != null && targetRegex.length > 0) {
				for (String target : targetRegex) {
					targetFilter.add(new UrlFilter(target));
				}
			} else {
				targetFilter.add(new UrlFilter());
			}
			if (assistRegex != null && assistRegex.length > 0) {
				for (String assist : assistRegex) {
					assistFilter.add(new UrlFilter(assist));
				}
			} else {
//				assistFilter.add(new UrlFilter());
			}
			DocumentAnalyser da = new ZhongChouAnalyser(targetFilter,
					assistFilter);
			sp.setDocumentAnalyser(da);
			return sp;
		}
	}

	public void start() {
		started = true;
		urlScheduler.addUrls(urlSeeds);		
		lastProcessTime = System.currentTimeMillis();
		String url = null;
		try {
			output.init(fileName);
		} catch (IOException e1) {			
			e1.printStackTrace();
			return;
		}
		output.setPageLimited(pageLimited);
		output.start();
		while (started && !output.isPageEnough()) {
			if ((url = urlScheduler.featchUrl()) != null) {
				lastProcessTime = System.currentTimeMillis();
				Runnable task = new SpiderTask(url, urlScheduler, downloader,
						analyser, output);
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
	
	void setPageCount(long pageCount) {
		this.pageLimited = pageCount;
	}
	
	void setOutputFile(String outputName) {
		this.fileName = outputName;
	}

	public void stop() {
		stopInner();
	}

	void setDocumentAnalyser(DocumentAnalyser analyser) {
		this.analyser = analyser;
	}

	private void stopInner() {
		synchronized (obj) {
			started = false;
		}
		threadPool.shutdown();
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

	private String[] urlSeeds;
}
