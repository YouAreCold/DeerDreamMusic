package com.xky.roll.music_service.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.xky.roll.music_service.mapper.SysHospitalInfoMapper;
import com.xky.roll.music_service.pojo.SysHospitalInfo;
import com.xky.roll.music_service.service.SysHospitalInfoService;
import com.xky.roll.music_service.util.StringTool;

@Service
public class SysHospitalInfoServiceImpl implements SysHospitalInfoService {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private SysHospitalInfoMapper sysHospitalInfoMapper;

	@Override
	public List<SysHospitalInfo> getHospitalList(String page) {
		int pageNum = 0;
		if (!StringTool.isNull(page)) {
			pageNum = Integer.valueOf(page);
			pageNum = (pageNum <= 0) ? 1 : pageNum;
		}
		Example example = new Example(SysHospitalInfo.class);
		example.createCriteria().andEqualTo("status", 1);
		PageHelper.startPage(pageNum, 5);
		List<SysHospitalInfo> sysHospitalList = sysHospitalInfoMapper.selectByExample(example);
		return sysHospitalList;
	}

	@Override
	public int addHospital(SysHospitalInfo sysHospitalInfo) {
		SysHospitalInfo existSysHospitalInfo = sysHospitalInfoMapper
				.selectByPrimaryKey(sysHospitalInfo.getHospitalId());
		if (existSysHospitalInfo != null) {
			return 0;
		}
		try {
			sysHospitalInfo.setCreateTime(sdf.parse(sdf.format(new Date())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sysHospitalInfoMapper.insert(sysHospitalInfo);
	}

	@Override
	public void updateHospital(SysHospitalInfo sysHospitalInfo) {
		sysHospitalInfoMapper.updateByPrimaryKeySelective(sysHospitalInfo);
	}

	@Override
	public int getHospitalCount() {
		Example example = new Example(SysHospitalInfo.class);
		return sysHospitalInfoMapper.selectCountByExample(example);
	}

	@Override
	public SysHospitalInfo findHospitalById(String hospitalId) {
		return sysHospitalInfoMapper.selectByPrimaryKey(hospitalId);
	}

	@Override
	public void deleteHospital(SysHospitalInfo sysHospitalInfo) {
		sysHospitalInfoMapper.deleteByPrimaryKey(sysHospitalInfo.getHospitalId());
	}

	@Override
	public List<SysHospitalInfo> queryHospital(SysHospitalInfo sysHospitalInfo) {
		Example example = new Example(SysHospitalInfo.class);
		if (sysHospitalInfo.getHospitalId() == null) {
			example.createCriteria().andLike("hospitalName", "%" + sysHospitalInfo.getHospitalName() + "%");
		} else {
			example.createCriteria().andLike("hospitalId", "%" + sysHospitalInfo.getHospitalId().toString() + "%");
		}
		List<SysHospitalInfo> list = sysHospitalInfoMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<SysHospitalInfo> findHospitalByHisId(String hisId) {
		Example example = new Example(SysHospitalInfo.class);
		example.createCriteria().andEqualTo("hisId", hisId);
		return sysHospitalInfoMapper.selectByExample(example);
	}

}
