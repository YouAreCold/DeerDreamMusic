package com.xky.roll.music_api.util.webservice;

import net.sf.json.JSONObject;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xky.roll.music_api.util.xstream.XStreamUtil;

/**
 * WebService接口工具类
 * @Description:TODO(调用webservice所用工具类，所有驱动转化为需继承此类)
 * @author:liyifan
 * @time:2017年5月27日 上午10:37:19
 */
public class WSUtil {
	public static Logger logger = LoggerFactory.getLogger(WSUtil.class);
	private String wsdlUrl;

	public WSUtil(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
	}

	/**
	 * 
	* @param json
	* @param method
	* @param params
	* @param urlAppend
	* @return
	* @throws Exception
	 */
	public Object getHISResponse(JSONObject json, String method, Object[] params,String medicalStatus) throws Exception {

		/*//获取地址分割索引
		int index = -1;
		
		if(wsdlUrl.indexOf("|") != -1){
			index = wsdlUrl.indexOf("|");
		}
		System.out.println(wsdlUrl.substring(0,index));
		System.out.println(wsdlUrl.substring(index + 1 , wsdlUrl.length()));*/
		
		//判断 取诊前或诊中的接口地址   zq 诊前   zz 诊中
		if(medicalStatus.equals("zq") && wsdlUrl.indexOf("|") != -1){
			wsdlUrl = wsdlUrl.substring(0,wsdlUrl.indexOf("|"));
		}
		else if(medicalStatus.equals("zz") && wsdlUrl.indexOf("|") != -1){
			wsdlUrl = wsdlUrl.substring(wsdlUrl.indexOf("|") + 1 , wsdlUrl.length());
		}
		
		String retString = null;
		Object[] returnStr = null;
		returnStr = callInterface(method, params);
		if(returnStr != null)
			retString = XStreamUtil.bean2Xml(returnStr[0]);
		
		return retString == null ? "" : retString;
	}

	public Object[] callInterface(String method, Object[] params) {
		Object[] returnStr = null;
		try {
			returnStr = getClient().invoke( method, params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用webService失败*********************");
		}
		return returnStr;
	}

	public Client getClient() {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(wsdlUrl);
		try {
			HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
			httpConduit.getClient().setReceiveTimeout(0);
			HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
			httpClientPolicy.setConnectionTimeout(6000000);	// 连接超时(毫秒)
			httpClientPolicy.setAllowChunking(false);		// 取消块编码
			httpClientPolicy.setReceiveTimeout(6000000);	// 响应超时(毫秒)

			httpConduit.getTarget().getAddress().setValue(wsdlUrl.substring(0, wsdlUrl.indexOf("?") + 1));
		} catch (Exception e) {
			System.out.println("---------------------------------------------");
			e.printStackTrace();
		}
		logger.debug("************获取webservice客户端成功");
		
		return client;
	}

	// json 转数组，子类重写此方法
	public Object[] toParams(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}

	// 将HIS的响应转化为平台统一响应（视具体方法而定），子类重写此方法
	public CustomResponse resultToCustomResponse(String result) {
		// TODO Auto-generated method stub
		return null;
	}

}
