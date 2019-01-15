package com.xky.roll.music_service.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xky.roll.music_service.pojo.SysMenu;
@Transactional(value="masterTransactionManager")
public interface SysMenuService {

	List<SysMenu> selectMenu();

	// 查询sys_menu表中总记录条数
	int countMenu();

	// 查询每页的记录
	List<SysMenu> pageNumList(String page);

	List<SysMenu> getParentMenu();

	void insertMenu(SysMenu menu);

	int deleteMenu(int menuId);

	SysMenu selectById(int menuId);

	void updateMenu(SysMenu menu);

}
