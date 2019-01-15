package com.xky.roll.music_api.service;

import net.sf.json.JSONObject;

import com.xky.roll.music_api.util.webservice.CustomResponse;

public interface OpenApiService {

	public CustomResponse getResult(JSONObject json);

}
