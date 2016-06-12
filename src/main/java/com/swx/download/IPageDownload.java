package com.swx.download;

import com.swx.common.Callback;

public interface IPageDownload <T>{	
	 void downloadPage(String url,Callback<T> callback);
}
