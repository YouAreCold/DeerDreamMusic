package com.xky.roll.music_service.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xky.roll.music_service.pojo.SysHospitalInfo;
@Transactional(value="masterTransactionManager")
public interface SysHospitalInfoService {

	List<SysHospitalInfo> getHospitalList(String pageNum);

	int addHospital(SysHospitalInfo sysHospitalInfo);

	void updateHospital(SysHospitalInfo sysHospitalInfo);

	int getHospitalCount();

	SysHospitalInfo findHospitalById(String hospitalId);

	void deleteHospital(SysHospitalInfo sysHospitalInfo);

	List<SysHospitalInfo> queryHospital(SysHospitalInfo sysHospitalInfo);
	
	 List<SysHospitalInfo> findHospitalByHisId(String hisId);

}
