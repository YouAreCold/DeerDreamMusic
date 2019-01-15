package com.xky.roll.music_api.util.http;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

public abstract interface DealResponse {

	public Object onSuccess(CloseableHttpResponse arg0) throws IOException;

	public void onFailure(HttpUriRequest arg0, Exception arg);
	
	public void callBack(String url, Object message);
	
}
