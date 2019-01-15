package com.xky.roll.music_service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import com.xky.roll.music_api.util.IdGenerator.IdGeneratorUtil;
import com.xky.roll.music_api.util.http.HttpUtil;
import com.xky.roll.music_service.pojo.SysMusicSong;
import com.xky.roll.music_service.service.SysMusicSongService;

/**
 * 获取第三方音乐  控制Controller层
 * @author wjx  
 *
 */
@Controller
@RequestMapping("/musicapi")
public class MusicApiController {
	
	// 日志信息
	public static Logger logger = LoggerFactory.getLogger(MusicApiController.class);
	
	/**
	 * 根据条件查询基础数据（第三方）
	 */
	@RequestMapping(value = "/queryMusicAndName", method = RequestMethod.POST)
	@ResponseBody
	public String queryMusicAndName(@RequestParam String keywork,@RequestParam String page,@RequestParam String pagesize,@RequestParam String source){
		logger.info("查询收到参数(第三方):[keyword:"+keywork+"],[page:"+page+"],[pagesize:"+pagesize+"]");
		
		// JSON入参
		JSONObject jsonData = new JSONObject();
		jsonData.put("orgUuid", source);				// 平台ID
		jsonData.put("orgPage",page);					// 页数
		jsonData.put("orgKeyWord", keywork);			// 关键字
		jsonData.put("orgPageSize", pagesize);			// 记录数
		// 拉取 歌曲搜索列表   start { --------------------------------------------------------------------------------------
		String searchMusicString = execute("http://127.0.0.1:8089/DeerDreamMusic/interface?m=HB002",jsonData);
		// 解析 歌曲搜索列表    start { ------------------------------------------------------------------------------
		JSONObject searchMusicJsonObj = JSONObject.fromObject(searchMusicString);
		// 使用迭代器存储
		Iterator<Object> searchMusicResultIt = getJSONArrayIteratorFromData(searchMusicJsonObj, "data");
		// 最终返回的数据
		JSONArray musicArray = new JSONArray();
		// 循环遍历迭代器
		while (searchMusicResultIt.hasNext()) {
			JSONObject results = (JSONObject) searchMusicResultIt.next();
			JSONObject musicObj = new JSONObject();
			musicObj.put("id", results.getString("orgFileMId"));																	// 歌曲MID
			musicObj.put("name", results.get("orgSongTitle"));																		// 歌曲名
			musicObj.put("singer", results.get("orgSingerName").toString().replace("[", "").replace("]", "").replace("\"", ""));	// 歌手名字
			musicObj.put("img", results.get("orgPicUrl"));																			//专辑路径
			musicObj.put("lrc", results.get("orgFileMId"));																			// 歌曲MID
			musicObj.put("src", results.getString("orgSongUrl"));																	// 歌曲文件路径
			musicObj.put("album", results.getString("orgAlbumName"));																// 专辑名
			musicObj.put("source", results.getString("source"));										 							// 所属平台
			musicObj.put("count","20");
			musicArray.element(musicObj);
		}
		//musicArray.add("pageCount"+musicArray.size());
		// 解析 歌曲搜索列表    end }----------------------------------------------------------------------------------
					
		// 拉取 歌曲搜索列表   end }-----------------------------------------------------------------------------------------
		
		return musicArray.toString();
	}
	
	/**
	 * 根据音乐ID查询数据（第三方）
	 */
	@RequestMapping(value = "/queryInfo", method = RequestMethod.POST)
	@ResponseBody
	public String queryMusicInfo(@RequestParam String musicId,@RequestParam String source){
		logger.info("查询收到参数:[musicId:"+musicId+"]");
		// JSON入参
		JSONObject jsonData = new JSONObject();
		
		// 拉取 歌词  start {------------------------------------------------------
		jsonData.clear();  					// 换入参并清空原来的入参
		jsonData.put("orgUuid", source);								// 平台ID
		jsonData.put("orgFileMId", musicId);							// 文件fileMID
		String lyricString = execute("http://127.0.0.1:8089/DeerDreamMusic/interface?m=HQ001",jsonData);
		// 解析歌词    start {
		String orgLyric = null;
		JSONObject lyricJsonObj = JSONObject.fromObject(lyricString);
		Iterator<Object> lyricResultIt = getJSONArrayIteratorFromData(lyricJsonObj, "data");
		while (lyricResultIt.hasNext()) {
			JSONObject lyricResults = (JSONObject) lyricResultIt.next();
			orgLyric = lyricResults.getString("orgLyric");
		}
		// 解析 歌词    end }
		// 拉取 歌词  end  }---------------------------------------------------------
		
		// 拉取 歌曲详情  start {------------------------------------------------------
		jsonData.clear();  												// 换入参并清空原来的入参
		jsonData.put("orgUuid", source);								// 平台ID
		jsonData.put("orgFileMId", musicId);	// 文件FileMID
		jsonData.put("orgFileType", "json");							// 数据格式
		String querySongString = execute("http://127.0.0.1:8089/DeerDreamMusic/interface?m=HB003",jsonData);
		// 解析歌曲详情    start {
		String orgSongUrl = null;
		String orgPicUrl = null;
		JSONObject querySongJsonObj = JSONObject.fromObject(querySongString);
		Iterator<Object> querySongResultIt = getJSONArrayIteratorFromData(querySongJsonObj, "data");
		while (querySongResultIt.hasNext()) {
			JSONObject songResults = (JSONObject) querySongResultIt.next();
			orgSongUrl = songResults.getString("orgSongUrl");
			orgPicUrl = songResults.getString("orgPicUrl");
		}
		// 解析 歌曲详情     end }
		
		// 拉取 歌曲详情   end  }---------------------------------------------------------
		
		SysMusicSong musicSong = new SysMusicSong();
		musicSong.setMusicId(musicId);
		
		JSONArray musicArray = new JSONArray();
		JSONObject musicObj = new JSONObject();
		musicObj.put("lrc", orgLyric);
		musicObj.put("src", orgSongUrl);
		musicObj.put("img", orgPicUrl);
		musicArray.element(musicObj);
		return musicArray.toString();
	
	}
	
	/**
	 * 根据用户ID查询歌单（网易云）
	 */
	@RequestMapping(value = "/queryUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public String queryUserInfo(@RequestParam String userId,@RequestParam String source){
		logger.info("查询收到参数:[userId:"+userId+"]");
		// JSON入参
		JSONObject jsonData = new JSONObject();
		
		// 拉取 用户歌单  start {------------------------------------------------------
		jsonData.clear();  					// 换入参并清空原来的入参
		jsonData.put("orgUuid", source);							// 平台ID
		jsonData.put("orgUserId", userId);							// 文件fileMID
		String userInfoResp = execute("http://127.0.0.1:8089/DeerDreamMusic/interface?m=HQ003",jsonData);
		JSONObject jsonObj = JSONObject.fromObject(userInfoResp);
		Iterator<Object> resultIt = getJSONArrayIteratorFromData(jsonObj, "data");
		
		JSONArray musicArray = new JSONArray();
		while (resultIt.hasNext()) {
			JSONObject results = (JSONObject) resultIt.next();
			JSONObject musicObj = new JSONObject();
			musicObj.put("tags", results.getString("tags"));
			musicObj.put("coverImgUrl", results.getString("coverImgUrl"));
			musicObj.put("playCount", results.getString("playCount"));
			musicObj.put("userId", results.getString("userId"));
			musicObj.put("updateTime", results.getString("updateTime"));
			musicObj.put("description", results.getString("description"));
			musicObj.put("name", results.getString("name"));
			musicObj.put("id", results.getString("id"));
			musicObj.put("source", results.getString("source"));
			musicObj.put("avatarUrl", results.getString("avatarUrl"));
			musicObj.put("nickname", results.getString("nickname"));
			musicObj.put("userType", results.getString("userType"));
			musicObj.put("backgroundUrl", results.getString("backgroundUrl"));
			musicArray.element(musicObj);
		}
		// 拉取 用户歌单  end  }---------------------------------------------------------
		
		return musicArray.toString();
	
	}
	
	/**
	 * 根据歌单ID读取歌单（网易云）
	 */
	@RequestMapping(value = "/queryUserInfoPlayList", method = RequestMethod.POST)
	@ResponseBody
	public String queryUserInfoPlayList(@RequestParam String pId,@RequestParam String source){
		logger.info("查询收到参数:[pId:"+pId+"]");
		// JSON入参
		JSONObject jsonData = new JSONObject();
		
		// 拉取 用户歌单  start {------------------------------------------------------
		jsonData.clear();  					// 换入参并清空原来的入参
		jsonData.put("orgUuid", source);							// 平台ID
		jsonData.put("pId", pId);							// 文件fileMID
		String userInfoResp = execute("http://127.0.0.1:8089/DeerDreamMusic/interface?m=HQ004",jsonData);
		JSONObject jsonObj = JSONObject.fromObject(userInfoResp);
		Iterator<Object> resultIt = getJSONArrayIteratorFromData(jsonObj, "data");
		
		JSONArray musicArray = new JSONArray();
		while (resultIt.hasNext()) {
			JSONObject results = (JSONObject) resultIt.next();
			JSONObject musicObj = new JSONObject();
			musicObj.put("name", results.getString("name"));
			musicObj.put("id", results.getString("id"));
			musicObj.put("singerName", results.getString("singerName").replace("[", "").replace("]", "").replace("\"", ""));
			musicObj.put("alId", results.getString("alId"));
			musicObj.put("alName", results.getString("alName"));
			musicObj.put("picUrl", results.getString("picUrl"));
			musicObj.put("publishTime", results.getString("publishTime"));
			musicObj.put("avatarUrl", results.getString("avatarUrl"));
			musicObj.put("userId", results.getString("userId"));
			musicObj.put("nickname", results.getString("nickname"));
			musicObj.put("signature", results.getString("signature"));
			musicObj.put("backgroundUrl", results.getString("backgroundUrl"));
			musicObj.put("coverImgUrl", results.getString("coverImgUrl"));
			musicObj.put("coverName", results.getString("coverName"));
			musicArray.element(musicObj);
		}
		// 拉取 用户歌单  end  }---------------------------------------------------------
		
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
