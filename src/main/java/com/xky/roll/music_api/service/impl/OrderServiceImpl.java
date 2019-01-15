package com.xky.roll.music_api.service.impl;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xky.roll.music_api.mapper.OrderMapper;
import com.xky.roll.music_api.pojo.Order;
import com.xky.roll.music_api.service.OrderService;
import com.xky.roll.music_api.util.InterfaceUtil;
import com.xky.roll.music_api.util.webservice.CustomResponse;
import com.xky.roll.music_service.mapper.SysHospitalInfoMapper;
import com.xky.roll.music_service.pojo.SysHospitalInfo;

@Service
public class OrderServiceImpl implements OrderService {
	public static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	@Autowired
	private SysHospitalInfoMapper sysHospitalInfoMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Override
	public void insertOrder(Order order) {
		try {
			orderMapper.insert(order);
		} catch (Exception e) {
			logger.error("插入订单失败", e);
		}
	}

	@Override
	public String updateOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * webservice方式
	 */
	@SuppressWarnings("unused")
	@Override
	public CustomResponse setOrder(JSONObject json) {
		Integer hospitalId = json.getInt("hospitalId");
		if (hospitalId == null) {
			return new CustomResponse(false, "医院编码不能为空！");
		}
		SysHospitalInfo sysHospiyalInfo = sysHospitalInfoMapper.selectByPrimaryKey(hospitalId);
		if (sysHospiyalInfo == null) {
			return new CustomResponse(false, "输入的医院编码有误！");
		}
		logger.debug("*********开始调用webservice方法");
		return new InterfaceUtil().callInterface(json, sysHospiyalInfo);
	}

}
