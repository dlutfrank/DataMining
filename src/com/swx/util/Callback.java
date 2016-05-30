package com.swx.util;

public interface Callback<T> {
	void onSuccess(T result);
	void onFaild(int code,String msg);
}
