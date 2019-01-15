package com.xky.roll.music_service.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xky.roll.music_api.quartz.TaskSetting;
import com.xky.roll.music_api.util.IdGenerator.IdGeneratorUtil;
import com.xky.roll.music_api.util.http.HttpUtil;
import com.xky.roll.music_service.pojo.SysMusicSong;
import com.xky.roll.music_service.pojo.TaskScheduleJob;
import com.xky.roll.music_service.service.SysMusicSongService;

/**
 * 拉取平台数据 控制Controller层
 * @author wjx  
 *
 */
@Controller
@RequestMapping("/pullData")
public class MusicTaskPullDataController {
	
	// 注入音乐数据管理 Service层
	@Autowired
	private SysMusicSongService sysMusicSongService;
	
	@Autowired
	private TaskSetting taskSetting;

	// 时间格式化
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping(value = "/testExcel", method = RequestMethod.GET)
	@ResponseBody
	public void testExcel(MultipartFile file){
		
	}
	
	/**
	 * 拉取数据 并存入数据库
	 */
	@RequestMapping(value = "/qqmusic", method = RequestMethod.GET)
	@ResponseBody
	public String pullData(@RequestParam("keywork") String keywork){
		// 拉取歌曲数量
		int songCount = 0;
		// 每次搜索 取500页 数据
		for (int page = 0; page < 500; page++) {
			// JSON入参
			JSONObject jsonData = new JSONObject();
			jsonData.put("orgUuid", "0001");		// 平台ID
			jsonData.put("orgPage", ""+(page+1));	// 页数
			jsonData.put("orgKeyWord", keywork);	// 关键字
			jsonData.put("orgPageSize", "30");		// 记录数
			
			// 拉取 歌曲搜索列表   start { --------------------------------------------------------------------------------------
			String searchMusicString = execute("http://127.0.0.1:8089/DeerDreamMusic/interface?m=HB002",jsonData);
			// 解析 歌曲搜索列表    start { ------------------------------------------------------------------------------
			JSONObject searchMusicJsonObj = JSONObject.fromObject(searchMusicString);
			
			// 当没数据的时候就结束
			if(searchMusicJsonObj.getString("data").equals("") || searchMusicJsonObj.getString("data") == null) break;
			
			// 使用迭代器存储
			Iterator<Object> searchMusicResultIt = getJSONArrayIteratorFromData(searchMusicJsonObj, "data");
			//JSONArray searchMusicAll = new JSONArray();
			List<Map<String, String>> searchMusicList = new ArrayList<Map<String,String>>();
			// 循环遍历迭代器
			while (searchMusicResultIt.hasNext()) {
				JSONObject results = (JSONObject) searchMusicResultIt.next();
				Map<String, String> searchMusicMap = new HashMap<String, String>();  		// 存储单首歌曲的详细信息
				searchMusicMap.put("orgAlbumId", results.getString("orgAlbumId"));			// 专辑ID
				searchMusicMap.put("orgAlbumName", results.getString("orgAlbumName"));		// 专辑名
				searchMusicMap.put("orgAlbumMId", results.getString("orgAlbumMId"));		// 专辑MID
				searchMusicMap.put("orgSongTitle", results.getString("orgSongTitle"));		// 歌曲名
				searchMusicMap.put("orgSongTime", results.getString("orgSongTime"));		// 歌曲发布时间
				searchMusicMap.put("orgSingerName", results.getString("orgSingerName"));	// 歌手名字
				searchMusicMap.put("orgSingerId", results.getString("orgSingerId"));		// 歌手ID
				searchMusicMap.put("orgSingerMId", results.getString("orgSingerMId")); 		// 歌手MID
				searchMusicMap.put("orgCode", results.getString("orgCode"));				// 状态码
				
				// 拉取 歌曲详情  start {------------------------------------------------------
				jsonData.clear();  					// 换入参并清空原来的入参
				jsonData.put("orgUuid", "0001");								// 平台ID
				jsonData.put("orgFileMId", results.getString("orgFileMId"));	// 文件FileMID
				jsonData.put("orgFileType", "json");							// 数据格式
				String querySongString = execute("http://127.0.0.1:8089/DeerDreamMusic/interface?m=HB003",jsonData);
				// 解析歌曲详情    start {
				String orgSongUrl = null;
				JSONObject querySongJsonObj = JSONObject.fromObject(querySongString);
				Iterator<Object> querySongResultIt = getJSONArrayIteratorFromData(querySongJsonObj, "data");
				while (querySongResultIt.hasNext()) {
					JSONObject songResults = (JSONObject) querySongResultIt.next();
					orgSongUrl = songResults.getString("orgSongUrl");
				}
				// 解析 歌曲详情    end }
				searchMusicMap.put("orgSongUrl", orgSongUrl);		// 歌曲文件路径
				// 拉取 歌曲详情  end  }---------------------------------------------------------
				
				// 拉取 歌词  start {------------------------------------------------------
				jsonData.clear();  					// 换入参并清空原来的入参
				jsonData.put("orgUuid", "0001");								// 平台ID
				jsonData.put("orgFileMId", results.getString("orgFileMId"));	// 文件fileMID
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
				searchMusicMap.put("orgLyric", orgLyric);									// 歌词
				// 拉取 歌词  end  }---------------------------------------------------------
				
				searchMusicList.add(searchMusicMap);										// 存储每首歌的Map
			}
			// 解析 歌曲搜索列表    end }----------------------------------------------------------------------------------
			
			// 拉取 歌曲搜索列表   end }-----------------------------------------------------------------------------------------

			// 将获取到的歌曲 存储到数据库  start { -------------------------------------------------------------------------------
			for (int i = 0; i < searchMusicList.size(); i++) {
				Map<String, String> musicMap = searchMusicList.get(i);
				//int count = sysMusicSongService.insertMusic(new SysMusicSong(new IdGeneratorUtil().getId(),"hello","ss","15634641"));
				SysMusicSong musicSong = new SysMusicSong();					
				musicSong.setMusicId(new IdGeneratorUtil().getId());			// 主键生成
				musicSong.setMusicLyric(musicMap.get("orgLyric"));				// 歌词
				musicSong.setMusicPlatformId("0001");							// 平台ID
				musicSong.setMusicSinglename(musicMap.get("orgSingerName"));	// 歌手名字
				musicSong.setMusicSongname(musicMap.get("orgSongTitle"));		// 歌曲名字
				musicSong.setMusicSongtime(musicMap.get("orgSongTime"));		// 歌曲发布时间
				musicSong.setMusicUrl(musicMap.get("orgSongUrl"));				// 歌曲路径
				musicSong.setMusicAlbumname(musicMap.get("orgAlbumName"));     	// 专辑名
				musicSong.setMusicSource("tencent");							// 平台名
				int num = (int)Double.parseDouble(musicMap.get("orgAlbumId"))%100; // 图片路径要拼接的参数
				musicSong.setMusicAlbumimageUrl("http://imgcache.qq.com/music/photo/album_300/" + num + "/300_albumpic_" + musicMap.get("orgAlbumId") + "_0.jpg");  			
				int recount = sysMusicSongService.insertMusic(musicSong);	
				if(recount == 1){
					songCount++; // 记录拉取歌的数量
				}else{
					System.out.println("添加QQMusic中，第"+(i+1)+"条失败！");
				}
			}
			// 将获取到的歌曲 存储到数据库  end } -------------------------------------------------------------------------------
		}
		return bulidJson("0","拉取音乐完成",songCount).toString();
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
