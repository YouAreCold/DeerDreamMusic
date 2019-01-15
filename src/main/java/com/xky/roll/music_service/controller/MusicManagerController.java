package com.xky.roll.music_service.controller;

import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xky.roll.music_api.util.http.HttpUtil;
import com.xky.roll.music_service.pojo.SysMusicSong;
import com.xky.roll.music_service.service.SysMusicSongService;
/**
 * 音乐管理  控制Controller层
 * @author wjx  
 *
 */
@Controller
@RequestMapping("/musicmanager")
public class MusicManagerController {
	
	// 注入音乐数据管理 Service层
	@Autowired
	private SysMusicSongService sysMusicSongService;
	
	// 日志信息
	public static Logger logger = LoggerFactory.getLogger(MusicManagerController.class);
	
	/**
	 * 根据条件查询全部数据
	 */
	@RequestMapping(value = "/queryMusicAndName", method = RequestMethod.POST)
	@ResponseBody
	public String queryMusicAndName(@RequestParam String keywork,@RequestParam String page,@RequestParam String pagesize){
		
		logger.info("查询收到参数:[keyword:"+keywork+"],[page:"+page+"],[pagesize:"+pagesize+"]");
		JSONArray musicArray = new JSONArray();
		// 关键字查询  start --------------------------------------
		SysMusicSong musicSong = new SysMusicSong();
		musicSong.setMusicSongname(keywork);
		musicSong.setMusicAlbumname(keywork);
		musicSong.setMusicSinglename(keywork);
		musicSong.setMusicLyric(keywork);
		List<SysMusicSong> musicSongList = sysMusicSongService.getMusicListOrName(musicSong,page,pagesize);
		// 关键字查询  end ------------------------------------------
		/*for (SysMusicSong sysMusicSong : musicSongList) {
			JSONObject musicTitle = new JSONObject();
			musicTitle.put("basic", true);
			musicTitle.put("name", keywork);
			musicTitle.put("singer", sysMusicSong.getMusicSinglename().replace("[", "").replace("]", "").replace("\"", ""));
			musicTitle.put("img", sysMusicSong.getMusicAlbumimageUrl());
			musicArray.element(musicTitle);
			break;
		}*/
		JSONObject musicTitle = new JSONObject();
		musicTitle.put("count", sysMusicSongService.getMusicListOrNameCount(musicSong, pagesize));
		musicArray.element(musicTitle);
		for (SysMusicSong sysMusicSong : musicSongList) {
			JSONObject musicObj = new JSONObject();
			musicObj.put("id", sysMusicSong.getMusicId());
			musicObj.put("name", sysMusicSong.getMusicSongname());
			musicObj.put("singer", sysMusicSong.getMusicSinglename().replace("[", "").replace("]", "").replace("\"", ""));
			musicObj.put("img", sysMusicSong.getMusicAlbumimageUrl());
			musicObj.put("lrc", sysMusicSong.getMusicLyric());
			musicObj.put("src", sysMusicSong.getMusicUrl());
			musicObj.put("album", sysMusicSong.getMusicAlbumname());
			musicObj.put("source", sysMusicSong.getMusicSource());
			musicArray.element(musicObj);
		}
		//musicArray.add("pageCount"+sysMusicSongService.getMusicListOrNameCount(musicSong, pagesize));
		
		return musicArray.toString();
	}
	
	/**
	 * 根据音乐ID查询数据
	 */
	@RequestMapping(value = "/queryMusicInfo", method = RequestMethod.POST)
	@ResponseBody
	public String queryMusicInfo(@RequestParam String musicId){
		logger.info("查询收到参数:[musicId:"+musicId+"]");
		SysMusicSong musicSong = new SysMusicSong();
		musicSong.setMusicId(musicId);
		SysMusicSong sysMusicSong = sysMusicSongService.findMusicById(musicSong);
		JSONArray musicArray = new JSONArray();
		JSONObject musicObj = new JSONObject();
		musicObj.put("name", sysMusicSong.getMusicSongname());
		musicObj.put("singer", sysMusicSong.getMusicSinglename().replace("[", "").replace("]", "").replace("\"", ""));
		musicObj.put("img", sysMusicSong.getMusicAlbumimageUrl());
		musicObj.put("lrc", sysMusicSong.getMusicLyric());
		musicObj.put("src", sysMusicSong.getMusicUrl());
		musicObj.put("album", sysMusicSong.getMusicAlbumname());
		musicObj.put("source", sysMusicSong.getMusicSource());
		musicArray.element(musicObj);
		return musicArray.toString();
	}
	
	/**
	 * 调用HttpClient方法 调用平台
	 * 
	 * @author wjx
	*/
    public String execute(String url, Object params) {
		HttpUtil util = new HttpUtil();
		String result = util.startInvoke(url, params,null);
		return result;
	}
    
    /**
	 * 
	 * @todo:得到JSONArray迭代对象
	 * @author wjx
	 * @date: 2017年8月25日 下午2:43:27
	 * @param outjson
	 * @param target
	 * @return
	 */
	private Iterator<Object> getJSONArrayIteratorFromData(JSONObject outjson, String target) {
		// 请求成功
		String data = outjson.getString(target);
		// 判断结果是否只有一条数据
		Object json1 = new JSONTokener(data).nextValue();
		JSONArray js = new JSONArray();
		if (json1 instanceof JSONObject) {
			JSONObject jsonObject = (JSONObject) json1;
			js.add(jsonObject);
		} else if (json1 instanceof JSONArray) {
			js = (JSONArray) json1;
		}
		Iterator<Object> it = js.iterator();
		return it;
	}
	
	/**
	 * 返回结果  格式化JSON
	 *  
	 * @param code:标识符
	 * @param array:json数组
	 * @param msg:消息
	 * @param queryCount:总条数
	 * @author wjx
	 */
	public JSONObject bulidJson(String code, String msg,int queryCount) {
		JSONObject json = new JSONObject();
		json.accumulate("state", code);
		json.accumulate("count", queryCount);
		json.accumulate("msg", msg == null ? "" : msg);
		return json;
	}
	
}
