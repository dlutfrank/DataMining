package com.swx.filter;

public interface IFilter <T>{
	boolean isValidate(T content);
}
