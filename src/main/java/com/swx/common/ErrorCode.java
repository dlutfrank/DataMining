package com.swx.common;

public enum ErrorCode {
	SUCCESS(0), IO_ERROR(-1000), PARAM_INVAILD(-1001), PAGE_DOWNLOAD_URL_INVAILD(
			-3000), UNKNOWN(-9999);

	private int mCode;

	private ErrorCode(int code) {
		mCode = code;
	}

	public int getCode() {
		return mCode;
	}

	public static ErrorCode valueOf(int code) {
		for (ErrorCode errorCode : ErrorCode.values()) {
			if (code == errorCode.getCode()) {
				return errorCode;
			}
		}
		return UNKNOWN;
	}
}
