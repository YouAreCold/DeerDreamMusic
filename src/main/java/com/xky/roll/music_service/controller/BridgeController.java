package com.xky.roll.music_service.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xky.roll.music_service.pojo.Interface;
import com.xky.roll.music_service.pojo.MethodFields;
import com.xky.roll.music_service.pojo.PlatformMethod;
import com.xky.roll.music_service.pojo.RuleMethod;
import com.xky.roll.music_service.pojo.ServiceResult;
import com.xky.roll.music_service.pojo.SysUser;
import com.xky.roll.music_service.service.InterfaceService;
import com.xky.roll.music_service.service.MethodFieldsService;
import com.xky.roll.music_service.service.PlatformMethodService;
import com.xky.roll.music_service.service.RuleMethodService;

/**
 * 
 * @Description:TODO(接口列表的增删改查)
 * @author:liyifan
 * @time:2017年6月27日 下午2:49:52
 */
@Controller
@RequestMapping("/bridge")
public class BridgeController {

	@Autowired
	private PlatformMethodService platformMethodServiceImpl;
	@Autowired
	private InterfaceService interfaceServiceImpl;
	@Autowired
	private MethodFieldsService methodFieldsServiceImpl;
	@Autowired
	private RuleMethodService ruleMethodServiceImpl;

	/**
	 * 填充平台方法下拉框
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getPlatMethodList")
	public List<PlatformMethod> getPlatMethodList() {
		return platformMethodServiceImpl.getPlatMethodList();
	}

	/**
	 * 填充已有入(出)参下拉框
	 * 
	 * @param methodId
	 *            平台方法id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMethodFieldsList")
	public List<MethodFields> getMethodFieldsList(Integer methodId) {
		if (methodId == null) {
			return null;
		}
		return methodFieldsServiceImpl.getMethodFieldsList(methodId);
	}

	/**
	 * 填充入（出）参规则下拉框
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRuleMethodList")
	public List<RuleMethod> getRuleMethodList() {
		return ruleMethodServiceImpl.getRuleMethodList();
	}

	/**
	 * 根据音乐平台id查询已有接口
	 * 
	 * @param hospitalId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getInterfaceList/{hospitalId}")
	public List<Interface> getInterfaceList(@PathVariable Integer hospitalId) {
		if (hospitalId == null) {
			return null;
		}
		return interfaceServiceImpl.getInterfaceList(hospitalId);
	}

	/**
	 * 插入新接口
	 * 
	 * @param inter
	 */
	@ResponseBody
	@RequestMapping("/addUpdateInterface")
	public ServiceResult addInterface(@RequestBody Interface inter, HttpServletRequest req) {
		ServiceResult result = new ServiceResult();
		SysUser user = (SysUser) req.getSession().getAttribute("user");
		// 验证(TODO)
		if (inter == null) {
			result.setStatus(ServiceResult.FAILED);
			result.setMsg("操作失败！");
			return result;
		}
		if (inter.getInterfaceId() == null) {
			return interfaceServiceImpl.addInterface(inter, user.getUserId());
		} else {
			return interfaceServiceImpl.updateInterface(inter, user.getUserId());
		}

	}

	/**
	 * 更新接口
	 * 
	 * @param inter
	 * @param req
	 */
	@ResponseBody
	@RequestMapping("/updateInterface")
	public void updateInterface(@RequestBody Interface inter, HttpServletRequest req) {
		SysUser user = (SysUser) req.getSession().getAttribute("user");
		// 验证(TODO)
		if (inter == null) {
			return;
		}
		interfaceServiceImpl.updateInterface(inter, user.getUserId());
	}

	/**
	 * 根据接口id查询
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getInterfaceById/{interId}")
	public Interface getInterfaceById(@PathVariable Integer interId) {
		if (interId == null) {
			return null;
		}
		Interface inter = interfaceServiceImpl.getInterfaceById(interId);
		return inter;
	}

	@ResponseBody
	@RequestMapping("/deleteInterfaceById/{hospitalId}/{currentId}")
	public ServiceResult deleteInterfaceById(@PathVariable Integer currentId, @PathVariable Integer hospitalId) {
		ServiceResult result = new ServiceResult();
		if (currentId == null) {
			result.setStatus(ServiceResult.FAILED);
			result.setMsg("操作失败");
			return result;
		}
		return interfaceServiceImpl.deleteInterfaceById(currentId, hospitalId);
	}

	@ResponseBody
	@RequestMapping("/getInterfaceByName/{hospitalId}/{name}")
	public ServiceResult getInterfaceByName(@PathVariable Integer hospitalId, @PathVariable String name) {
		ServiceResult result = new ServiceResult();
		if (hospitalId == null) {
			result.setStatus(ServiceResult.FAILED);
			result.setMsg("操作失败");
			return result;
		}
		return interfaceServiceImpl.getInterfaceByName(hospitalId, name);
	}
}
