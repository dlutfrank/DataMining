package com.swx;

import java.util.List;

import org.jsoup.nodes.Document;

import com.swx.analyse.DocumentAnalyser;
import com.swx.common.Callback;
import com.swx.common.ErrorCode;
import com.swx.download.JSoupDownloader;
import com.swx.output.FileOutputManager;
import com.swx.schedule.Scheduler;

public class SpiderTask implements Runnable {
	private String url;
	private Scheduler s;
	private JSoupDownloader downloader;
	private DocumentAnalyser analyser;
	private FileOutputManager output;

	public SpiderTask(String url, Scheduler s, JSoupDownloader downloader,
			DocumentAnalyser analyser, FileOutputManager output) {
		this.url = url;
		this.s = s;
		this.downloader = downloader;
		this.analyser = analyser;
		this.output = output;
	}

	@Override
	public void run() {
		if (url == null || url.isEmpty() || s == null || downloader == null
				|| analyser == null || output == null) {
			return;
		}
		System.out.println(url);
		downloader.downloadPage(url, new Callback<Document>() {

			@Override
			public void onSuccess(Document result) {
				String str = analyser.analyse(result,url);
				List<String>urls = analyser.extractUrl(result);				
				s.addUrls(urls);	
				if(urls != null){
					System.out.println("SpiderTask::run()"+urls);	
				}
				if(str != null) {
					System.out.println("SpiderTask::run()"+str);					
				}				
				output.addTask(str);				
			}

			@Override
			public void onFaild(ErrorCode code, String msg) {
				System.out.println("SpiderTask::run()"+code.toString() + msg);
			}

		});
	}

}
