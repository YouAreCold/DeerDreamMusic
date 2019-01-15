package com.xky.roll.music_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xky.roll.music_service.pojo.SysMenu;
import com.xky.roll.music_service.service.SysMenuService;

/**
 * 
 * @ClassName: HomePageController
 * @Description: TODO(登陆后首页)
 * @Author liyifan
 * @Date 2017年5月18日 下午9:17:49
 */
@Controller
public class HomePageController {
	@Autowired
	private SysMenuService sysMenuService;

	@RequestMapping("/homePage")
	public String homePage(Model model) {
		List<SysMenu> list = sysMenuService.selectMenu();
		model.addAttribute("list", list);
		return "homePage";
	}

	// 查询父节点下面所有的子节点
	@RequestMapping(value = "/listMenu")
	public String listMenu(Model model) {
		int count = sysMenuService.countMenu();
		model.addAttribute("menuCount", count);
		return "menuList2";
	}

	@ResponseBody
	@RequestMapping(value = "/querylist/{pageNo}", method = { RequestMethod.POST })
	public List<SysMenu> queryParam(@PathVariable("pageNo") String pageNo) {
		List<SysMenu> list = sysMenuService.pageNumList(pageNo);
		return list;
	}

	// 跳转到添加节点页面
	@ResponseBody
	@RequestMapping(value = "/toaddMenu")
	public List<SysMenu> toaddMenu() {
		List<SysMenu> list = sysMenuService.getParentMenu();
		return list;
	}

	// 插入节点
	@RequestMapping(value = "/insertMenu")
	public void insertMenu(@RequestBody SysMenu menu) {
		sysMenuService.insertMenu(menu);

	}

	// 删除节点
	@ResponseBody
	@RequestMapping(value = "/deleteMenu/{menuId}")
	public int deleteMenu(@PathVariable int menuId) {
		return sysMenuService.deleteMenu(menuId);
	}

	// 修改
	@ResponseBody
	@RequestMapping(value = "/update/{menuId}")
	public SysMenu updatePage(@PathVariable int menuId) {
		SysMenu sysMenu = sysMenuService.selectById(menuId);
		return sysMenu;
	}

	@RequestMapping(value = "/commitUpdate")
	public void commitUpdate(@RequestBody SysMenu menu) {
		sysMenuService.updateMenu(menu);
	}
}
