package com.xky.roll.music_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xky.roll.music_service.pojo.SysHospitalInfo;
import com.xky.roll.music_service.service.SysHospitalInfoService;

/**
 * 
 * @ClassName: SysHospitalController
 * @Description: TODO(音乐平台信息的增删改查)
 * @Author liyifan
 * @Date 2017年5月22日 上午10:46:04
 */
@Controller
@RequestMapping("/hospital")
public class SysHospitalController {
	@Autowired
	private SysHospitalInfoService sysHospitalInfoServiceImpl;

	@RequestMapping("/toHospitalList")
	public String toHospitalList(Model model) {
		int hospitalCount = sysHospitalInfoServiceImpl.getHospitalCount();
		model.addAttribute("hospitalCount", hospitalCount);
		return "hospitalInfo";
	}

	@ResponseBody
	@RequestMapping("/getHospitalList/{pageNum}")
	public List<SysHospitalInfo> getHospitalList(Model model, @PathVariable String pageNum) {
		List<SysHospitalInfo> hospitalList = sysHospitalInfoServiceImpl.getHospitalList(pageNum);
		return hospitalList;
	}

	@ResponseBody
	@RequestMapping("/addHospital")
	public int addHospital(@RequestBody SysHospitalInfo sysHospitalInfo) {
		return sysHospitalInfoServiceImpl.addHospital(sysHospitalInfo);
	}

	@ResponseBody
	@RequestMapping("/updateHospital")
	public void updateHospital(@RequestBody SysHospitalInfo sysHospitalInfo) {
		sysHospitalInfoServiceImpl.updateHospital(sysHospitalInfo);
	}

	@ResponseBody
	@RequestMapping("/deleteHospital")
	public void deleteHospital(@RequestBody SysHospitalInfo sysHospitalInfo) {
		sysHospitalInfoServiceImpl.deleteHospital(sysHospitalInfo);
	}

	@ResponseBody
	@RequestMapping("/getHospitalDetail/{hospitalId}")
	public SysHospitalInfo getHospitalDetail(@PathVariable String hospitalId) {
		return sysHospitalInfoServiceImpl.findHospitalById(hospitalId);
	}

	@ResponseBody
	@RequestMapping("/queryHospital")
	public List<SysHospitalInfo> queryHospital(@RequestBody SysHospitalInfo sysHospitalInfo) {
		return sysHospitalInfoServiceImpl.queryHospital(sysHospitalInfo);
	}
}
