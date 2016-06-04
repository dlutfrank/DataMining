package com.swx.common;

public interface Callback<T> {
	void onSuccess(T result);
	void onFaild(int code,String msg);
}
