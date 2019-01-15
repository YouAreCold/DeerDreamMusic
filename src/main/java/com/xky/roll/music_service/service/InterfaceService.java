package com.xky.roll.music_service.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xky.roll.music_service.pojo.Interface;
import com.xky.roll.music_service.pojo.ServiceResult;

@Transactional(value = "masterTransactionManager")
public interface InterfaceService {

	List<Interface> getInterfaceList(Integer hospitalId);

	ServiceResult addInterface(Interface inter, Integer userId);

	Interface getInterfaceById(Integer interId);

	ServiceResult updateInterface(Interface inter, Integer userId);

	ServiceResult deleteInterfaceById(Integer currentId,Integer hospitalId);

	ServiceResult getInterfaceByName(Integer hospitalId, String name);

}
