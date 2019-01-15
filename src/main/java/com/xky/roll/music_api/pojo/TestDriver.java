package com.xky.roll.music_api.pojo;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xky.roll.music_api.util.webservice.CustomResponse;
import com.xky.roll.music_api.util.webservice.WSUtil;

/**
 * 
 * @Description:TODO(测试驱动类)
 * @author:liyifan
 * @time:2017年5月27日 下午4:44:23
 */
public class TestDriver extends WSUtil {
	public static Logger logger = LoggerFactory.getLogger(TestDriver.class);

	public TestDriver(String wsdlUrl) {
		super(wsdlUrl);
	}

//	@Override
//	public String getHISResponse(JSONObject json) throws Exception {
//		Object hisReturn = null;
//		String method = json.getString("methodName");
//		// Object[] params = toParams(json);
//		// 测试参数数组，实际需通过上述方法转化
//		Object[] params = new Object[] { "", "" };
//		logger.debug("****************调用目标方法");
//		hisReturn = super.getClient().invoke(method, params);
//		return hisReturn == null ? "" : hisReturn.toString();
//	}

	@Override
	public Object[] toParams(JSONObject json) {
		// TODO Auto-generated method stub
		return super.toParams(json);
	}

	@Override
	public CustomResponse resultToCustomResponse(String result) {
		// TODO Auto-generated method stub
		return super.resultToCustomResponse(result);
	}

}
