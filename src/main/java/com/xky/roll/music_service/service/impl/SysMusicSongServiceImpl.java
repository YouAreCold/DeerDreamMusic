package com.xky.roll.music_service.service.impl;

import java.util.List;

import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.xky.roll.music_service.mapper.SysMusicSongMapper;
import com.xky.roll.music_service.pojo.SysMusicSong;
import com.xky.roll.music_service.service.SysMusicSongService;
import com.xky.roll.music_service.util.StringTool;

/**
 * 音乐管理 Service实现类
 * @author wjx  
 *
 */
@Service
public class SysMusicSongServiceImpl implements SysMusicSongService {
	
	// 注入SysMusicSongMapper
	@Autowired
	private SysMusicSongMapper sysMusicSongMapper;
	
	public static Logger logger = LoggerFactory.getLogger(SysMusicSongService.class);
	
	/*@Retryable(value = { RemoteAccessException.class }, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 1))
	public void call() throws Exception {
		logger.info(LocalTime.now()+" do something...");
		throw new RemoteAccessException("RPC调用异常");
	}

	@Recover
	public void recover(RemoteAccessException e) {
		logger.info(e.getMessage());
	}*/
	
	// 增加音乐数据
	@Override
	public int insertMusic(SysMusicSong sysMusicSong) {
		// TODO Auto-generated method stub
		return sysMusicSongMapper.insert(sysMusicSong);
	}

	// 查询全部数据
	@Override
	public List<SysMusicSong> getMusicList() {
		// TODO Auto-generated method stub
		return sysMusicSongMapper.selectAll();
	}
	
	// 修改音乐数据
	@Override
	public int updateMusic(SysMusicSong sysMusicSong) {
		// TODO Auto-generated method stub
		return sysMusicSongMapper.updateByPrimaryKey(sysMusicSong);
	}

	// 根据主键查询音乐信息
	@Override
	public SysMusicSong findMusicById(SysMusicSong sysMusicSong) {
		// TODO Auto-generated method stub
		return sysMusicSongMapper.selectOne(sysMusicSong);
	}

	// 根据主键 删除音乐
	@Override
	public int deleteMusic(SysMusicSong sysMusicSong) {
		return sysMusicSongMapper.deleteByPrimaryKey(sysMusicSong.getMusicId());
		
	}

	// 分页查询
	@Override
	public List<SysMusicSong> getMusicPageList(String page) {
		int pageNum = 0;
		if (!StringTool.isNull(page)) {
			pageNum = Integer.valueOf(page);
			pageNum = (pageNum <= 0) ? 1 : pageNum;
		}
		Example example = new Example(SysMusicSong.class);
		PageHelper.startPage(pageNum, 10);
		List<SysMusicSong> list = sysMusicSongMapper.selectByExample(example);
		return list;
	}

	// 根据条件查询
	@Override
	public List<SysMusicSong> getMusicListOrName(SysMusicSong sysMusicSong,String page,String pagesize) {
		int pageNum = 0;
		if (!StringTool.isNull(page)) {
			pageNum = Integer.valueOf(page);
			pageNum = (pageNum <= 0) ? 1 : pageNum;
		}
		Example example = new Example(SysMusicSong.class);
		example.createCriteria().orLike("musicSongname", "%" + sysMusicSong.getMusicSongname() + "%").orLike("musicAlbumname", "%" + sysMusicSong.getMusicAlbumname() + "%").orLike("musicSinglename", "%" + sysMusicSong.getMusicSinglename() + "%").orLike("musicLyric", "%" + sysMusicSong.getMusicLyric() + "%");
		PageHelper.startPage(pageNum, Integer.parseInt(pagesize));
		List<SysMusicSong> songList = sysMusicSongMapper.selectByExample(example);
		return songList;
	}

	// 根据条件查询的总页数
	@Override
	public int getMusicListOrNamePageCount(SysMusicSong sysMusicSong,
			String pagesize) {
		Example example = new Example(SysMusicSong.class);
		example.createCriteria().orLike("musicSongname", "%" + sysMusicSong.getMusicSongname() + "%").orLike("musicAlbumname", "%" + sysMusicSong.getMusicAlbumname() + "%").orLike("musicSinglename", "%" + sysMusicSong.getMusicSinglename() + "%").orLike("musicLyric", "%" + sysMusicSong.getMusicLyric() + "%");
		int iRowCount = sysMusicSongMapper.selectCountByExample(example);
		int iPageCount = (iRowCount + Integer.valueOf(pagesize) - 1) / Integer.valueOf(pagesize);
		return iPageCount;
	}
	
	// 根据条件查询的总数量
	@Override
	public int getMusicListOrNameCount(SysMusicSong sysMusicSong,
			String pagesize) {
		Example example = new Example(SysMusicSong.class);
		example.createCriteria().orLike("musicSongname", "%" + sysMusicSong.getMusicSongname() + "%").orLike("musicAlbumname", "%" + sysMusicSong.getMusicAlbumname() + "%").orLike("musicSinglename", "%" + sysMusicSong.getMusicSinglename() + "%").orLike("musicLyric", "%" + sysMusicSong.getMusicLyric() + "%");
		int iRowCount = sysMusicSongMapper.selectCountByExample(example);
		return iRowCount;
	}
}
