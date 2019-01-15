package com.xky.roll.music_api.service.impl;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xky.roll.music_api.service.OpenApiService;
import com.xky.roll.music_api.util.InterfaceUtil;
import com.xky.roll.music_api.util.webservice.CustomResponse;
import com.xky.roll.music_service.mapper.SysHospitalInfoMapper;
import com.xky.roll.music_service.pojo.SysHospitalInfo;

@Service
public class OpenApiServiceImpl implements OpenApiService {
	public static Logger logger = LoggerFactory.getLogger(OpenApiServiceImpl.class);
	@Autowired
	private SysHospitalInfoMapper sysHospitalInfoMapper;

	/**
	 * 接口方式
	 */
	@Override
	public CustomResponse getResult(JSONObject json) {
		String hospitalId = json.getString("orgUuid");
		logger.info("[hospitalID]:"+hospitalId);
		if (hospitalId == null) {
			return new CustomResponse(false, "-------hospital code not null！");
		}
		SysHospitalInfo sysHospiyalInfo = sysHospitalInfoMapper.selectByPrimaryKey(hospitalId);
		if (sysHospiyalInfo == null) {
			return new CustomResponse(false, "-----hospital code error！");
		}
		logger.debug("*********-------Start Call Interface");
		json.put("userName", sysHospiyalInfo.getUserName());
		json.put("password", sysHospiyalInfo.getPassword());
		json.put("hisId", sysHospiyalInfo.getHisId());
		return new InterfaceUtil().callInterface(json, sysHospiyalInfo);
	}

}
