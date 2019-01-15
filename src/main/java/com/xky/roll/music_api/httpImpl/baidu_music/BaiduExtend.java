package com.xky.roll.music_api.httpImpl.baidu_music;

import java.util.Date;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.xky.roll.music_api.util.http.HttpUtil;
import com.xky.roll.music_service.util.DateUtil;

/**
 * 网易云 api实现类(未开发)
 * 
 * @author wjx
 *
 */
public class BaiduExtend extends HttpUtil {

	/**
	 * 重写父类doGet方法
	 * @param url
	 */
	@Override
	public String doGet(String url, Object param, String requestProperty) {
		// 地址栏 POST参数 入参  
		JSONObject jsonData = JSONObject.fromObject(param);
		// 处理返回对应的功能号
		switch (jsonData.getString("restMethod")) {
			case "searchMusic":
				return searchMusic(jsonData, url, param, requestProperty);//查询歌曲搜索列表
			case "querySong":
				return querySong(jsonData, url, param, requestProperty);// 查询歌曲详情
			case "queryLyric":
				return queryLyric(jsonData, url, param, requestProperty);// 查询歌词详情
			default:
				return bulidJson("-1", null, "请选择正确的平台功能号！", 0).toString();// 没找到对应的功能号
		}
	}
	
	// 音乐平台 功能方法模块  start -------------------------------------------------------------------------------------------
	
	/**
	 * 查询歌词详情
	 * @author wjx
	 */
	private String queryLyric(JSONObject jsonData,String url, Object param, String requestProperty) {

		JSONObject json = new JSONObject();
		String songUrlResp = super.doGet("http://music.baidu.com/data/music/fmlink?songIds="+jsonData.getString("orgFileMId")+"&rate=320", null,"https://musicapi.qianqian.com");
		String orgLyric = super.doGet(JSONObject.fromObject(songUrlResp).getJSONObject("data").getJSONArray("songList").getJSONObject(0).getString("lrcLink"), null,"https://musicapi.qianqian.com");
		// 处理Array数组
		JSONArray dataAll = new JSONArray();
		
		// 存入 单条数据数组 start ---------------------------------------------------------
		JSONObject objectall = new JSONObject();
		 objectall.put("orgLyric", orgLyric);									// 歌词
	        objectall.put("orgCode", "0");										// 状态码
	        objectall.put("source", "0004");									// 所属平台
	     // 存入 单条数据数组 end ------------------------------------------------------------
	     
	     dataAll.add(objectall);
		// 返回结果
		json = bulidJson("0", dataAll, "BaiDu_Music Deatil Success",1);
		return json.toString();
	}

	/**
	 * 查询歌曲详情 
	 * @author wjx
	 */
	private String querySong(JSONObject jsonData,String url, Object param, String requestProperty) {

		JSONObject json = new JSONObject();
		String songUrlResp = super.doGet("http://music.baidu.com/data/music/fmlink?songIds="+jsonData.getString("orgFileMId")+"&rate=320", null,"https://musicapi.qianqian.com");
		String songUrl = JSONObject.fromObject(songUrlResp).getJSONObject("data").getJSONArray("songList").getJSONObject(0).getString("songLink");
		String songPicBig = JSONObject.fromObject(songUrlResp).getJSONObject("data").getJSONArray("songList").getJSONObject(0).getString("lrcLink");
		if("".equals(songPicBig)){
			songPicBig = "http://127.0.0.1:8089/DeerDreamMusic/DeerDreamMusic-View/images/other-images.jpg";
    	}
        // 处理Array数组
		JSONArray dataAll = new JSONArray();
		
		// 存入 单条数据数组 start ---------------------------------------------------------
		JSONObject objectall = new JSONObject();
	     objectall.put("orgSongUrl", songUrl);									// 歌曲路径
	    objectall.put("orgPicUrl", songPicBig);									// 专辑路径
	     objectall.put("source", "0004");										// 所属平台
	     objectall.put("orgCode", "0");									// 状态码
	     // 存入 单条数据数组 end ------------------------------------------------------------
	     
	     dataAll.add(objectall);
		// 返回结果
		json = bulidJson("0", dataAll, "BaiDu_Music Deatil Success",1);
		return json.toString();
		
	}
	
	/**
	 * 查询歌曲搜索列表 
	 * @author wjx
	 */
	private String searchMusic(JSONObject jsonData,String url, Object param, String requestProperty) {
		// 最终返回的jsonobject对象
		JSONObject json = new JSONObject();
		//get参数
		StringBuilder searchMusicUrl = new StringBuilder();
		searchMusicUrl.append(url+"/v1/restserver/ting?");
		// 页码 与 记录数  空的时候 给默认值
		String page = (jsonData.getString("orgPage").equals(""))?("1"):(jsonData.getString("orgPage"));
		String pageSize = (jsonData.getString("orgPageSize").equals(""))?("30"):(jsonData.getString("orgPageSize"));
		searchMusicUrl.append("method=baidu.ting.search.merge");
		searchMusicUrl.append("&isNew=1");
		searchMusicUrl.append("&query="+jsonData.getString("orgKeyWord"));
		searchMusicUrl.append("&page_size="+pageSize);
		searchMusicUrl.append("&page_no="+page);
		searchMusicUrl.append("&type=0");
		searchMusicUrl.append("&format=json");
		searchMusicUrl.append("&from=ios");
		searchMusicUrl.append("&channel=(null)");
		searchMusicUrl.append("&cuid=appstore");
		searchMusicUrl.append("&from=ios");
		searchMusicUrl.append("&version=5.9.12");
		
		//得到平台的返回数据
		String response = super.doGet(searchMusicUrl.toString(), null,"https://musicapi.qianqian.com");
		// 处理返回数据
		JSONArray jsonArray = JSONObject.fromObject(response).getJSONObject("result").getJSONObject("song_info").getJSONArray("song_list");
		//搜索总条数
		int queryCount = jsonArray.size();
		
		// 返回Array数组
		JSONArray dataAll = new JSONArray();
		// 循环遍历出数据 json数组
		for (int i = 0; i < jsonArray.size(); i++) {
			// 单条数据
            JSONObject jsonObjects = jsonArray.getJSONObject(i);
            // 单条数据数组
            JSONObject objectall = new JSONObject();
            // 歌曲名
            String songName = jsonObjects.getString("title");
            String songUrlResp = super.doGet("http://music.baidu.com/data/music/fmlink?songIds="+jsonObjects.getString("song_id")+"&rate=320", null,"https://musicapi.qianqian.com");
            String songUrl = "";
            if(songUrlResp.indexOf("403") == -1){
            	songUrl = JSONObject.fromObject(songUrlResp).getJSONObject("data").getJSONArray("songList").getJSONObject(0).getString("songLink");
            }
           //String checkSongUrl = super.doGet(songUrl, null,"https://musicapi.qianqian.com");
            // 查询不到歌源的提示已下架
            if("".equals(songUrl)){
            	songName += "(蜡笔小鑫正在努力争取版权)";
            }
            // 存入 单条数据数组      start  -----------------------------------------------------------------------------------------------------------
			objectall.put("orgAlbumId", jsonObjects.getString("album_id"));														// 专辑ID
			objectall.put("orgAlbumName", jsonObjects.getString("album_title"));												// 专辑名
			objectall.put("orgFileMId", jsonObjects.getString("song_id"));														// 音频MID
			objectall.put("orgSongTitle", songName);																			// 歌曲名称
			objectall.put("orgSongTime", DateUtil.formatDate(new Date()));														// 歌曲发布时间
			objectall.put("orgSingerName", "["+jsonObjects.getString("author")+"]");											// 歌手名字
			objectall.put("orgSingerId", jsonObjects.getString("artist_id"));													// 歌手ID
			objectall.put("orgCode", 0);																						// 状态码
			objectall.put("orgSongUrl", songUrl);																				// 歌曲路径
			objectall.put("orgPicUrl", jsonObjects.getString("pic_small").replace("w_90", "w_400").replace("h_90", "w_400"));	// 专辑头像路径
			objectall.put("source", "0004");																					// 平台ID 
			// 存入 单条数据数组     end  ---------------------------------------------------------------------------------------------------------------
			
			// 小数组加进大数组
            dataAll.add(objectall);
        }
		// 返回结果
		json = bulidJson("0", dataAll, "BaiDu_Music Search Success",queryCount);
		return json.toString();
	}
	
	// 音乐平台 功能方法模块  end -------------------------------------------------------------------------------------------
	
	/**
	 * 返回结果  格式化JSON
	 *  
	 * @param code:标识符
	 * @param array:json数组
	 * @param msg:消息
	 * @param queryCount:总条数
	 * @author wjx
	 */
	public JSONObject bulidJson(String code, JSONArray array, String msg,int queryCount) {
		JSONObject json = new JSONObject();
		json.accumulate("state", code);
		json.accumulate("count", queryCount);
		json.accumulate("msg", msg == null ? "" : msg);
		json.accumulate("data", array);
		return json;
	}

	/**
	 * 判断那些非必填的字段并设置进去
	 * 
	 * @param json
	 * @param jsonData
	 * @param targetName
	 * @param sourceName
	 */
	private static void set(Object param, JSONObject jsonData, String targetName, String sourceName) {
		if (param instanceof JSONObject) {
			JSONObject jsonObject = (JSONObject) param;
			if (jsonData.containsKey(sourceName)) {
				jsonObject.put(targetName, jsonData.getString(sourceName));
			} else {
				jsonObject.put(targetName, "");
			}
		} else {
			Map<String, Object> map = (Map<String, Object>) param;
			if (jsonData.containsKey(sourceName)) {
				map.put(targetName, jsonData.getString(sourceName));
			} else {
				map.put(targetName, "");
			}
		}
	}
}
