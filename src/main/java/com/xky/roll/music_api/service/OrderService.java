package com.xky.roll.music_api.service;

import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Transactional;

import com.xky.roll.music_api.pojo.Order;
import com.xky.roll.music_api.util.webservice.CustomResponse;

@Transactional(value = "sqlserverTransactionManager")
public interface OrderService {

	public void insertOrder(Order order);

	public String updateOrder(Order order);

	public String deleteOrder(Order order);

	public CustomResponse setOrder(JSONObject json);

}
