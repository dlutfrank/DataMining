package com.swx.analyse;

import java.util.List;

import org.jsoup.nodes.Document;

public abstract class DataAnalyser<R, P> {

	public DataAnalyser() {
	}

	public abstract R analyse(P content);	
	
	public abstract List<String> extractUrl(P content);

}
