package com.xky.roll.music_service.mapper;

import tk.mybatis.mapper.common.Mapper;

import com.xky.roll.music_service.pojo.Interface;

public interface InterfaceMapper extends Mapper<Interface> {

	public Integer getId();

	public Interface selectRuleListByPrimaryKey(Integer interId);
}