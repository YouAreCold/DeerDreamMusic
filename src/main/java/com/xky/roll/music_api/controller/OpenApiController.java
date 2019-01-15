package com.xky.roll.music_api.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xky.roll.music_api.constall.ResultCode;
import com.xky.roll.music_api.constall.VsersionConstant;
import com.xky.roll.music_api.service.impl.OpenApiServiceImpl;
import com.xky.roll.music_api.util.webservice.CustomResponse;
import com.xky.roll.music_service.service.RecipeService;

/**
 * 调用第三方api接口  控制器
 * @author wjx  
 *
 */
@Controller
public class OpenApiController {
	public static Logger logger = LoggerFactory.getLogger(OpenApiController.class);
	@Autowired
	private OpenApiServiceImpl openApi;
	
	@Autowired
	private RecipeService recipeServiceImpl;
	
	/**
	 * 接收音乐平台 功能号
	 * @author wjx
	 */
	@RequestMapping(value = "/interface", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String query(HttpServletRequest request, @RequestBody String params) {
		logger.info("收到入参:"+params);
		String no = request.getParameter("m").trim();
		String result = "";
		if (VsersionConstant.QUERYHOS.equals(no)) { // 查询音乐平台接口
			// result = queryMusicPlatform(params);
		}
		if (VsersionConstant.QUERYMUSIC.equals(no)) { // 查询歌曲搜索列表
			result = searchMusic(params);
		}
		if (VsersionConstant.QUERYSONG.equals(no)) { // 查询歌曲详情
			result = querySong(params);
		}
		if (VsersionConstant.QUERYLYRIC.equals(no)) { // 查询歌词
			result = queryLyric(params);
		}
		if (VsersionConstant.USERINFO.equals(no)) { // 用户歌单查询
			result = userInfo(params);
		}
		if (VsersionConstant.USERINFOPLAYLIST.equals(no)) { // 读取用户歌单
			result = userInfoPlayList(params);
		}
		if (VsersionConstant.QUERYAVATAR.equals(no)) { // 查询歌手头像(大&小)
			result = queryAvatar(params);
		}
		if (VsersionConstant.ADDORDER.equals(no)) { // 下单
			result = setOrder(params);
		}
		if (VsersionConstant.PAYORDER.equals(no)) { // 支付
			result = setPayOrder(params);
		}
		if (VsersionConstant.CANNERORDER.equals(no)) { // 取消下单
			result = cancelOrder(params);
		}
		return result;

	}

	/**
	 * 查询歌曲搜索列表
	 * 
	 * @param request
	 *            content={"orgUuid":"311","cardNo":"AAAA",....}
	 * @return depList
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/searchMusic", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String searchMusic(String params) {
		JSONObject jsonData = JSONObject.fromObject(params);
		jsonData.put("restMethod", "searchMusic");
		try {
			CustomResponse response = openApi.getResult(jsonData);
			if (response == null || response.getStatus().equals("1")) {
				return bulidJson(ResultCode.ADD_FAILURE_HIS_FAIL, response.getMsg());
			}
			System.out.println("rep==>"+response.getData());
			JSONObject reuslt = JSONObject.fromObject(response.getData());
			return reuslt.toString();
		} catch (Exception e) {
			JSONObject object = new JSONObject();
			object.accumulate("state", "1");
			object.accumulate("msg", ResultCode.ADD_FAILURE_HIS_FAIL.getMsgCode());
			object.accumulate("data", "{}");
			return object.toString();
		}
	}

	/**
	 * 查询歌曲详情
	 * 
	 * @param request
	 *            content={"orgUuid":"311","cardNo":"AAAA",....}
	 * @return depList
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/querySong", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String querySong(String params) {
		JSONObject jsonData = JSONObject.fromObject(params);
		jsonData.put("restMethod", "querySong");
		try {
			CustomResponse response = openApi.getResult(jsonData);
			if (response == null || response.getStatus().equals("1")) {
				return bulidJson(ResultCode.ADD_FAILURE_HIS_FAIL, response.getMsg());
			}
			JSONObject reuslt = JSONObject.fromObject(response.getData());
			return reuslt.toString();
		} catch (Exception e) {
			JSONObject object = new JSONObject();
			object.accumulate("state", "1");
			object.accumulate("msg", ResultCode.ADD_FAILURE_HIS_FAIL.getMsgCode());
			object.accumulate("data", "{}");
			return object.toString();
		}
	}

	/**
	 * 查询歌词
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryLyric", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String queryLyric(String params) {
		JSONObject jsonData = JSONObject.fromObject(params);
		jsonData.put("restMethod", "queryLyric");
		try {
			CustomResponse response = openApi.getResult(jsonData);
			if (response == null || response.getStatus().equals("1")) {
				return bulidJson(ResultCode.ADD_FAILURE_HIS_FAIL, response.getMsg());
			}
			JSONObject reuslt = JSONObject.fromObject(response.getData());
			return reuslt.toString();
		} catch (Exception e) {
			JSONObject object = new JSONObject();
			object.accumulate("state", "1");
			object.accumulate("msg", ResultCode.ADD_FAILURE_HIS_FAIL.getMsgCode());
			object.accumulate("data", "{}");
			return object.toString();
		}

	}

	/**
	 * 查询歌手头像
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryAvatar", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String queryAvatar(String params) {
		JSONObject jsonData = JSONObject.fromObject(params);
		jsonData.put("restMethod", "queryAvatar");
		try {
			CustomResponse response = openApi.getResult(jsonData);
			if (response == null || response.getStatus().equals("1")) {
				return bulidJson(ResultCode.ADD_FAILURE_HIS_FAIL, response.getMsg());
			}
			JSONObject reuslt = JSONObject.fromObject(response.getData());
			return reuslt.toString();
		} catch (Exception e) {
			JSONObject object = new JSONObject();
			object.accumulate("state", "1");
			object.accumulate("msg", ResultCode.ADD_FAILURE_HIS_FAIL.getMsgCode());
			object.accumulate("data", "{}");
			return object.toString();
		}

	}
	
	/**
	 * 查询用户歌单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userInfoPlayList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String userInfoPlayList(String params) {
		JSONObject jsonData = JSONObject.fromObject(params);
		jsonData.put("restMethod", "userInfoPlayList");
		try {
			CustomResponse response = openApi.getResult(jsonData);
			if (response == null || response.getStatus().equals("1")) {
				return bulidJson(ResultCode.ADD_FAILURE_HIS_FAIL, response.getMsg());
			}
			JSONObject reuslt = JSONObject.fromObject(response.getData());
			return reuslt.toString();
		} catch (Exception e) {
			JSONObject object = new JSONObject();
			object.accumulate("state", "1");
			object.accumulate("msg", ResultCode.ADD_FAILURE_HIS_FAIL.getMsgCode());
			object.accumulate("data", "{}");
			return object.toString();
		}

	}
	
	/**
	 * 读取用户歌单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String userInfo(String params) {
		JSONObject jsonData = JSONObject.fromObject(params);
		jsonData.put("restMethod", "userInfo");
		try {
			CustomResponse response = openApi.getResult(jsonData);
			if (response == null || response.getStatus().equals("1")) {
				return bulidJson(ResultCode.ADD_FAILURE_HIS_FAIL, response.getMsg());
			}
			JSONObject reuslt = JSONObject.fromObject(response.getData());
			return reuslt.toString();
		} catch (Exception e) {
			JSONObject object = new JSONObject();
			object.accumulate("state", "1");
			object.accumulate("msg", ResultCode.ADD_FAILURE_HIS_FAIL.getMsgCode());
			object.accumulate("data", "{}");
			return object.toString();
		}

	}

	/**
	 * 下单接口
	 * 
	 * @param request
	 *            content={"orgUuid":"PSA1507377304","cardNo":"AAAA",....}
	 * @return orderId
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/setOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String setOrder(String params) {
		JSONObject jsonData = JSONObject.fromObject(params);
		jsonData.put("restMethod", "setOrder");
		try {
			CustomResponse response = openApi.getResult(jsonData);
			if (response == null || response.getStatus().equals("1")) {
				return bulidJson(ResultCode.ADD_FAILURE_HIS_FAIL, response.getMsg());
			}
			JSONObject reuslt = JSONObject.fromObject(response.getData());
			return reuslt.toString();
		} catch (Exception e) {
			JSONObject object = new JSONObject();
			object.accumulate("state", "1");
			object.accumulate("msg", ResultCode.ADD_FAILURE_HIS_FAIL.getMsgCode());
			object.accumulate("data", "{}");
			return object.toString();
		}
	}

	/**
	 * 支付接口
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/setPayOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String setPayOrder(String params) {
		JSONObject jsonData = JSONObject.fromObject(params);
		jsonData.put("restMethod", "setPayOrder");
		try {
			CustomResponse response = openApi.getResult(jsonData);
			if (response == null || response.getStatus().equals("1")) {
				return bulidJson(ResultCode.ADD_FAILURE_HIS_FAIL, response.getMsg());
			}
			JSONObject reuslt = JSONObject.fromObject(response.getData());
			return reuslt.toString();
		} catch (Exception e) {
			JSONObject object = new JSONObject();
			object.accumulate("state", "1");
			object.accumulate("msg", ResultCode.ADD_FAILURE_HIS_FAIL.getMsgCode());
			object.accumulate("data", "{}");
			return object.toString();
		}
	}

	/**
	 * 取消下单
	 * 
	 * @param request
	 * @return state：0 成功 1：失败
	 */
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String cancelOrder(String params) {
		JSONObject jsonData = JSONObject.fromObject(params);
		jsonData.put("restMethod", "cancelOrder");
		try {
			CustomResponse response = openApi.getResult(jsonData);
			if (response == null || response.getStatus().equals("1")) {
				return bulidJson(ResultCode.ADD_FAILURE_HIS_FAIL, response.getMsg());
			}
			JSONObject reuslt = JSONObject.fromObject(response.getData());
			return reuslt.toString();
		} catch (Exception e) {
			JSONObject object = new JSONObject();
			object.accumulate("state", "1");
			object.accumulate("msg", ResultCode.ADD_FAILURE_HIS_FAIL.getMsgCode());
			object.accumulate("data", "{}");
			return object.toString();
		}

	}

	/**
	 * 返回数据 统一格式
	* @param code
	* @param msg
	* @return
	 */
	public String bulidJson(ResultCode code, String msg) {
		JSONObject object = new JSONObject();
		if (ResultCode.ADD_FAILURE_HIS_FAIL.equals(code)) {
			object.accumulate("state", "500");
		} else if (ResultCode.ADD_FAILURE_OPENAPI_FAIL.equals(code)) {
			object.accumulate("state", "1");
		}
		object.accumulate("msg", msg);
		object.accumulate("data", "");
		return object.toString();
	}
}
