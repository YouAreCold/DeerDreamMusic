package com.xky.roll.music_api.util;

import java.lang.reflect.Constructor;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xky.roll.music_api.util.http.HttpUtil;
import com.xky.roll.music_api.util.webservice.CustomResponse;
import com.xky.roll.music_api.util.webservice.WSUtil;
import com.xky.roll.music_service.pojo.SysHospitalInfo;
import com.xky.roll.music_service.util.StringUtil;

/**
 * 
 * @ClassName: InterfaceUtil
 * @Description: TODO(webService 调用工具类)
 * @Author liyifan
 * @Date 2017年5月24日 下午5:43:20
 */
public class InterfaceUtil {
	public static Logger logger = LoggerFactory.getLogger(InterfaceUtil.class);
	
	private static final Integer POST = 1;
	private static final Integer GET = 2;
	private static final Integer WEBSERVICES = 3;
	private static final Integer HL7 = 4;
	
	
	public CustomResponse callInterface(JSONObject json, SysHospitalInfo sysHospiyalInfo){
		Integer way = sysHospiyalInfo.getApiWay();
		if(POST.equals(way)){
			 return this.doPost(json, sysHospiyalInfo);
		}else if(GET.equals(way)){
			 return this.doGet(json, sysHospiyalInfo);
		}else if(WEBSERVICES.equals(way)){
			return this.callWebService(json, sysHospiyalInfo);
		}else if(HL7.equals(way)){
			
		}
		return null;
	}
	

	
	public CustomResponse doPost(JSONObject json, SysHospitalInfo sysHospiyalInfo) {
		String url = sysHospiyalInfo.getInvokingUrl();
		CustomResponse reponst = new CustomResponse(true,"",null);
		try {
		if (StringUtil.isEmpty(url)) {
			//logger.debug("***********该音乐平台未配置调用URL");
             logger.info("***********Hospital not url");
			return null;
		}
		HttpUtil post = null;
		if (StringUtil.isEmpty(sysHospiyalInfo.getDriveTransClass())) {
			// 没有驱动转化类
			post = new HttpUtil();
		} else {
			// 获取驱动转化类
				Class<?> cls = Class.forName(sysHospiyalInfo.getDriveTransClass());
				post = (HttpUtil) cls.newInstance();
		}
		String hsiResult = "";
		hsiResult = post.startInvoke(url, json.toString(),null);
		 reponst = reponst.data(hsiResult);
		} catch (Exception e) {
			logger.error("------Music Error", e);
			CustomResponse faile = new CustomResponse(false,"",null);
			return faile;
		}
		return reponst;
	}
	
	public CustomResponse doGet(JSONObject json, SysHospitalInfo sysHospiyalInfo) {
		String url = sysHospiyalInfo.getInvokingUrl();
		CustomResponse reponst = new CustomResponse(true,"",null);
		try {
		if (StringUtil.isEmpty(url)) {
			logger.debug("***********Hospital not url");
			return null;
		}
		HttpUtil post = null;
		if (StringUtil.isEmpty(sysHospiyalInfo.getDriveTransClass())) {
			// 没有驱动转化类
			post = new HttpUtil();
		} else {
			// 获取驱动转化类
				Class<?> cls = Class.forName(sysHospiyalInfo.getDriveTransClass());
				post = (HttpUtil) cls.newInstance();
		}
		String hsiResult = "";
		hsiResult = post.doGet(url,json,null);
		 reponst = reponst.data(hsiResult);
		} catch (Exception e) {
			logger.error("----Music Interface Error", e);
			CustomResponse faile = new CustomResponse(false,"",null);
			return faile;
		}
		return reponst;
	}
	
	public CustomResponse callWebService(JSONObject json, SysHospitalInfo sysHospiyalInfo) {
		String wsdlUrl = sysHospiyalInfo.getInvokingUrl();
		CustomResponse reponst = new CustomResponse(true,"",null);
		if (StringUtil.isEmpty(wsdlUrl)) {
			logger.debug("***********Hospital not url");
			return null;
		}
		WSUtil ws = null;
		if (StringUtil.isEmpty(sysHospiyalInfo.getDriveTransClass())) {
			// 没有驱动转化类
			ws = new WSUtil(wsdlUrl);
		} else {
			// 获取驱动转化类
			try {
				Class<?> cls = Class.forName(sysHospiyalInfo.getDriveTransClass());
				Class[] paramTypes = { String.class };
				Constructor con = cls.getConstructor(paramTypes);
				Object[] wsParams = { wsdlUrl };
				ws = (WSUtil) con.newInstance(wsParams);
			} catch (Exception e) {
				logger.error("----Webservice Error", e);
				CustomResponse faile = new CustomResponse(false,"",null);
				return faile;
			}
		}
		Object hsiResult = "";
		try {
			hsiResult = ws.getHISResponse(json,null,null,"").toString();
			 reponst = reponst.data(hsiResult);
		} catch (Exception e) {
			logger.error("----Music Interface Error", e);
			CustomResponse faile = new CustomResponse(false,"",null);
			return faile;
		}
		return reponst;
	}

}
