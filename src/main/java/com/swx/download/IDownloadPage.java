package com.swx.download;

import com.swx.common.Callback;

public interface IDownloadPage {
	boolean isUrlVaild(String url);
	<T> void downloadPage(String url,Callback<T> callback);
}
