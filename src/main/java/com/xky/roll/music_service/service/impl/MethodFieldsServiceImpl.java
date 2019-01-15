package com.xky.roll.music_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.xky.roll.music_service.mapper.MethodFieldsMapper;
import com.xky.roll.music_service.pojo.MethodFields;
import com.xky.roll.music_service.service.MethodFieldsService;

@Service
public class MethodFieldsServiceImpl implements MethodFieldsService {
	@Autowired
	private MethodFieldsMapper methodFieldsMapper;

	@Override
	public List<MethodFields> getMethodFieldsList(Integer methodId) {
		Example example = new Example(MethodFields.class);
		example.createCriteria().andEqualTo("platformMethodId", methodId);
		return methodFieldsMapper.selectByExample(example);
	}

}
