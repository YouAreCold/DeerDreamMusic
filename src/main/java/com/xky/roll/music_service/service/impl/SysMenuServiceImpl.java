package com.xky.roll.music_service.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.xky.roll.music_service.mapper.SysMenuMapper;
import com.xky.roll.music_service.pojo.SysMenu;
import com.xky.roll.music_service.service.SysMenuService;
import com.xky.roll.music_service.util.StringTool;

@Service
public class SysMenuServiceImpl implements SysMenuService {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public List<SysMenu> selectMenu() {
		Example example = new Example(SysMenu.class);
		List<SysMenu> menuList = sysMenuMapper.selectByExample(example);
		return menuList;
	}

	@Override
	public int countMenu() {
		Example example = new Example(SysMenu.class);
		return sysMenuMapper.selectCountByExample(example);
	}

	@Override
	public List<SysMenu> pageNumList(String page) {
		int pageNum = 0;
		if (!StringTool.isNull(page)) {
			pageNum = Integer.valueOf(page);
			pageNum = (pageNum <= 0) ? 1 : pageNum;
		}
		PageHelper.startPage(pageNum, 10);
		List<SysMenu> list = sysMenuMapper.selectAll();
		return list;
	}

	@Override
	public List<SysMenu> getParentMenu() {
		Example example = new Example(SysMenu.class);
		example.createCriteria().andIsNull("parentId");
		return sysMenuMapper.selectByExample(example);
	}

	@Override
	public void insertMenu(SysMenu menu) {
		menu.setCreatetime(sdf.format(new Date()));
		sysMenuMapper.insert(menu);
	}

	@Override
	public int deleteMenu(int menuId) {
		return sysMenuMapper.deleteByPrimaryKey(menuId);
	}

	@Override
	public SysMenu selectById(int menuId) {
		return sysMenuMapper.selectByPrimaryKey(menuId);
	}

	@Override
	public void updateMenu(SysMenu menu) {
		sysMenuMapper.updateByPrimaryKey(menu);
	}
}
