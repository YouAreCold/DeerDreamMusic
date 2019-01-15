package com.xky.roll.music_service.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xky.roll.music_service.pojo.SysMusicSong;

/**
 * 音乐数据管理 接口类
 * @author wjx  
 *
 */
@Transactional(value="masterTransactionManager")
public interface SysMusicSongService {
	int insertMusic(SysMusicSong sysMusicSong);

	List<SysMusicSong> getMusicList();

	int updateMusic(SysMusicSong sysMusicSong);

	SysMusicSong findMusicById(SysMusicSong sysMusicSong);

	int deleteMusic(SysMusicSong sysMusicSong);
	
	List<SysMusicSong> getMusicPageList(String page);// 分页查询
	
	List<SysMusicSong> getMusicListOrName(SysMusicSong sysMusicSong,String page,String pagesize);// 根据条件做查询
	
	int getMusicListOrNameCount(SysMusicSong sysMusicSong,String pagesize);// 根据条件查询的总数量
	
	int getMusicListOrNamePageCount(SysMusicSong sysMusicSong,String pagesize);// 根据条件查询的总页数
	
	/*void call()throws Exception;// 测试重试异常
*/}
