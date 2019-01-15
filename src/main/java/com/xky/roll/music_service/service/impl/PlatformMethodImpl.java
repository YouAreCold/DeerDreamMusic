package com.xky.roll.music_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.xky.roll.music_service.mapper.PlatformMethodMapper;
import com.xky.roll.music_service.pojo.PlatformMethod;
import com.xky.roll.music_service.service.PlatformMethodService;

@Service
public class PlatformMethodImpl implements PlatformMethodService {
	@Autowired
	private PlatformMethodMapper platformMethodMapper;

	@Override
	public List<PlatformMethod> getPlatMethodList() {
		Example example = new Example(PlatformMethod.class);
		List<PlatformMethod> list = platformMethodMapper.selectByExample(example);
		return list;
	}

}
