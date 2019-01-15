package com.xky.roll.music_service.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.xky.roll.music_service.mapper.InterfaceMapper;
import com.xky.roll.music_service.mapper.RuleMapper;
import com.xky.roll.music_service.pojo.Interface;
import com.xky.roll.music_service.pojo.Rule;
import com.xky.roll.music_service.pojo.ServiceResult;
import com.xky.roll.music_service.service.InterfaceService;

@Service
public class InterfaceServiceImpl implements InterfaceService {
	@Autowired
	private InterfaceMapper interfaceMapper;
	@Autowired
	private RuleMapper ruleMapper;

	@Override
	public List<Interface> getInterfaceList(Integer hospitalId) {
		Example example = new Example(Interface.class);
		example.createCriteria().andEqualTo("hospitalId", hospitalId);
		return interfaceMapper.selectByExample(example);
	}

	/**
	 * 操作接口表、入参表、出参表、规则表
	 */
	@Override
	public ServiceResult addInterface(Interface inter, Integer userId) {
		Date now = new Date();
		ServiceResult result = new ServiceResult();
		inter.setCreateUser(userId);
		inter.setCreateTime(now);
		inter.setUpdateUser(userId);
		inter.setUpdateTime(now);
		interfaceMapper.insertSelective(inter);
		Integer interId = interfaceMapper.getId();
		// 已有入参map
		insertRule(inter.getHospitalId(), userId, interId, inter.getInList(), 0);
		// 已有出参map
		insertRule(inter.getHospitalId(), userId, interId, inter.getOutList(), 1);
		// 自定义入参map
		insertRule(inter.getHospitalId(), userId, interId, inter.getCustomInList(), 2);
		// 自定义出参map
		insertRule(inter.getHospitalId(), userId, interId, inter.getCustomOutList(), 3);
		result.setStatus(ServiceResult.SUCCESS);
		result.setMsg("添加成功！");
		return result;
	}

	private void insertRule(Integer hospitalId, Integer userId, Integer interId, List<Object> list, Integer flag) {
		Date now = new Date();
		for (int i = 0; i < list.size(); i++) {
			HashMap<Object, Object> map = (HashMap<Object, Object>) list.get(i);
			for (Map.Entry<Object, Object> entry : map.entrySet()) {
				if ("ruleId".equals(entry.getKey())) {
					continue;
				}
				Rule temp = new Rule();
				temp.setInterfaceId(interId);
				temp.setHospitalId(hospitalId);
				temp.setTargetFileds((String) entry.getKey());
				temp.setRuleMethodId(Integer.valueOf((String) entry.getValue()));
				if (flag == 0 || flag == 1) {
					temp.setExistOrCustom((byte) 0);
				} else {
					temp.setExistOrCustom((byte) 1);
				}
				if (flag == 0 || flag == 2) {
					temp.setInOrOut((byte) 0);
				} else {
					temp.setInOrOut((byte) 1);
				}
				temp.setCreateUser(userId);
				temp.setCreateTime(now);
				temp.setUpdateUser(userId);
				temp.setUpdateTime(now);
				ruleMapper.insertSelective(temp);
			}

		}
	}

	@Override
	public Interface getInterfaceById(Integer interId) {
		return interfaceMapper.selectRuleListByPrimaryKey(interId);
	}

	/**
	 * 更新接口表 规则表
	 */
	@Override
	public ServiceResult updateInterface(Interface inter, Integer userId) {
		ServiceResult result = new ServiceResult();
		Date now = new Date();
		inter.setUpdateUser(userId);
		Integer interId = inter.getInterfaceId();
		inter.setUpdateTime(now);
		interfaceMapper.updateByPrimaryKeySelective(inter);
		// 已有入参map
		updateRule(inter.getHospitalId(), userId, interId, inter.getInList(), 0);
		// 已有出参map
		updateRule(inter.getHospitalId(), userId, interId, inter.getOutList(), 1);
		// 自定义入参map
		updateRule(inter.getHospitalId(), userId, interId, inter.getCustomInList(), 2);
		// 自定义出参map
		updateRule(inter.getHospitalId(), userId, interId, inter.getCustomOutList(), 3);
		result.setStatus(ServiceResult.SUCCESS);
		result.setMsg("更新成功！");
		return result;

	}

	// 更新规则表
	private void updateRule(Integer hospitalId, Integer userId, Integer interId, List<Object> list, Integer flag) {
		// 获取原有规则list
		Date now = new Date();
		Example example = new Example(Rule.class);
		switch (flag) {
		case 0:
			example.createCriteria().andCondition("exist_or_custom=0 and in_or_out=0 and interface_id=" + interId);
			break;
		case 1:
			example.createCriteria().andCondition("exist_or_custom=0 and in_or_out=1 and interface_id=" + interId);
			break;
		case 2:
			example.createCriteria().andCondition("exist_or_custom=1 and in_or_out=0 and interface_id=" + interId);
			break;
		case 3:
			example.createCriteria().andCondition("exist_or_custom=1 and in_or_out=1 and interface_id=" + interId);
			break;
		default:
			break;
		}

		List<Rule> ruleList = ruleMapper.selectByExample(example);
		// 与现有list做对比、
		List<Integer> newList = new ArrayList<Integer>();
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<Object, Object> map = (HashMap<Object, Object>) list.get(i);
				Integer ruleId = null;
				if (map.get("ruleId") != null && map.get("ruleId") != "") {
					ruleId = Integer.valueOf((String) map.get("ruleId"));
				}
				if (ruleId == null) {// 做新增
					for (Map.Entry<Object, Object> entry : map.entrySet()) {
						if ("ruleId".equals(entry.getKey())) {
							continue;
						}
						Rule temp = new Rule();
						temp.setInterfaceId(interId);
						temp.setHospitalId(hospitalId);
						temp.setTargetFileds((String) entry.getKey());
						temp.setRuleMethodId(Integer.valueOf((String) entry.getValue()));
						if (flag == 0 || flag == 1) {
							temp.setExistOrCustom((byte) 0);
						} else {
							temp.setExistOrCustom((byte) 1);
						}
						if (flag == 0 || flag == 2) {
							temp.setInOrOut((byte) 0);
						} else {
							temp.setInOrOut((byte) 1);
						}
						temp.setCreateUser(userId);
						temp.setCreateTime(now);
						temp.setUpdateUser(userId);
						temp.setUpdateTime(now);
						ruleMapper.insertSelective(temp);
					}

				} else {// 做更新
					newList.add(ruleId);
					for (Map.Entry<Object, Object> entry : map.entrySet()) {
						if ("ruleId".equals(entry.getKey())) {
							continue;
						}
						Rule temp = new Rule();
						temp.setRuleId(ruleId);
						temp.setTargetFileds((String) entry.getKey());
						temp.setRuleMethodId(Integer.valueOf((String) entry.getValue()));
						temp.setUpdateUser(userId);
						temp.setUpdateTime(now);
						ruleMapper.updateByPrimaryKeySelective(temp);
					}
				}
			}
		}
		// 原先有 现在没有 做删除
		List<Integer> oldList = new ArrayList<Integer>();
		for (int j = 0; j < ruleList.size(); j++) {
			oldList.add(ruleList.get(j).getRuleId());
		}
		oldList.removeAll(newList);
		for (int i = 0; i < oldList.size(); i++) {
			ruleMapper.deleteByPrimaryKey(oldList.get(i));
		}
	}

	/**
	 * @param currentId
	 */
	@Override
	public ServiceResult deleteInterfaceById(Integer currentId, Integer hospitalId) {
		// 验证是否被其他接口调用
		ServiceResult result = new ServiceResult();
		Example example1 = new Example(Interface.class);
		// 获取所有有用到该接口的方法
		example1.createCriteria().andCondition("hospital_id =" + hospitalId + " and next_method_id LIKE '" + currentId
				+ ",%' OR next_method_id LIKE '%," + currentId + ",%' OR next_method_id LIKE '%," + currentId + "'");
		List<Interface> list = interfaceMapper.selectByExample(example1);
		if (list != null && list.size() != 0) {
			result.setStatus(ServiceResult.FAILED);
			result.setMsg("该接口已被其它接口调用！");
			return result;
		}
		interfaceMapper.deleteByPrimaryKey(currentId);
		Example example2 = new Example(Rule.class);
		example2.createCriteria().andEqualTo("interfaceId", currentId);
		ruleMapper.deleteByExample(example2);
		return result.ok();
	}

	@Override
	public ServiceResult getInterfaceByName(Integer hospitalId, String name) {
		ServiceResult result = new ServiceResult();
		Example example = new Example(Interface.class);
		// 获取所有有用到该接口的方法
		example.createCriteria().andCondition("hospital_id = " + hospitalId + " and his_method_name = '" + name+"'");
		List<Interface> list = interfaceMapper.selectByExample(example);
		if (list != null && list.size() != 0) {
			result.setStatus(ServiceResult.FAILED);
			result.setMsg("HIS方法名已存在！");
			return result;
		}
		return result.ok(list);
	}
}
