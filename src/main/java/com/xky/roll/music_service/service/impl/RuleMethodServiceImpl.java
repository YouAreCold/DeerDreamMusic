package com.xky.roll.music_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.xky.roll.music_service.mapper.RuleMethodMapper;
import com.xky.roll.music_service.pojo.RuleMethod;
import com.xky.roll.music_service.service.RuleMethodService;

@Service
public class RuleMethodServiceImpl implements RuleMethodService {
	@Autowired
	private RuleMethodMapper ruleMethodMapper;

	@Override
	public List<RuleMethod> getRuleMethodList() {
		Example example = new Example(RuleMethod.class);
		return ruleMethodMapper.selectByExample(example);
	}

}
