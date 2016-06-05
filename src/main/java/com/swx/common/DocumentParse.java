package com.swx.common;

import org.jsoup.nodes.Document;

public interface DocumentParse {
	public <T> T parse(Document doc);
}
