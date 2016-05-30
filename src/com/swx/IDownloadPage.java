package com.swx;

import com.swx.util.Callback;

public interface IDownloadPage {
	boolean isUrlVaild(String url);
	<T> void downloadPage(String url,Callback<T> callback);
}
