package com.xky.roll.music_api.httpImpl.qq_music;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.xky.roll.music_api.util.IdGenerator.IdGeneratorUtil;
import com.xky.roll.music_api.util.base64.Base64Util;
import com.xky.roll.music_api.util.http.HttpUtil;

/**
 * QQ音乐 api实现类
 * 
 * @author wjx
 *
 */
public class QQExtend extends HttpUtil {

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
		//get参数
		StringBuilder getParam = new StringBuilder();
		getParam.append("/lyric/fcgi-bin/fcg_query_lyric_new.fcg?");
		// 页码 与 记录数  空的时候 给默认值
		String fileMId = jsonData.getString("orgFileMId");
		getParam.append("songmid=" + fileMId);
		getParam.append("&g_tk=5381");
		// 拼接路径
		url = url + getParam.toString();
		//得到平台的返回数据
		String response = super.doGet(url, null, "https://y.qq.com").replace("MusicJsonCallback", "");
		response = response.substring(1, response.length() - 1 );
		
		// 验证返回值Code是否正确
		String ResultCode = JSONObject.fromObject(response).getString("code");
		if (Integer.valueOf(ResultCode) != 0) {
			json = bulidJson("9999", null, "API_Code Error",0);
			return json.toString();
		}
		// 处理返回数据
		String lyric = JSONObject.fromObject(response).getString("lyric");  //歌词
		lyric = Base64Util.getFromBase64(lyric);							// 歌词解密(base64解密)
		// 处理Array数组
		JSONArray dataAll = new JSONArray();
		
		// 存入 单条数据数组 start ---------------------------------------------------------
		JSONObject objectall = new JSONObject();
        objectall.put("orgLyric", lyric);									// 歌词
        objectall.put("orgCode", ResultCode);								// 状态码
        objectall.put("source", "0001");									// 所属平台
        // 存入 单条数据数组 end ------------------------------------------------------------
        
        dataAll.add(objectall);
		// 返回结果
		json = bulidJson(ResultCode, dataAll, "QQ_Music Lyric Success",1);
		return json.toString();
	}

	/**
	 * 查询歌曲详情 
	 * @author wjx
	 */
	private String querySong(JSONObject jsonData,String url, Object param, String requestProperty) {

		JSONObject json = new JSONObject();
		//get参数
		StringBuilder querySongUrl = new StringBuilder();
		querySongUrl.append(url + "/v8/fcg-bin/fcg_play_single_song.fcg?");
		// 音频ID 与  文件格式 
		String fileMId = jsonData.getString("orgFileMId");
		String fileType = jsonData.getString("orgFileType");
		querySongUrl.append("songmid=" + fileMId);
		querySongUrl.append("&platform=yqq");
		querySongUrl.append("&format="+((fileType.equals(""))?("json"):(fileType)));
		
		// 取vkey值   start
		// 1.产生guid随机值
		String guid = IdGeneratorUtil.getId().substring(0,10);
		// 2.拼接key的url地址
		StringBuilder keyUrl = new StringBuilder();
		keyUrl.append(url+"/base/fcgi-bin/fcg_musicexpress.fcg?");
		keyUrl.append("json=3");
		keyUrl.append("&guid="+guid);
		keyUrl.append("&format=json");
		// 3.获取vkey
		String vkey = super.doGet(keyUrl.toString(), null, requestProperty);
		vkey = JSONObject.fromObject(vkey).getString("key");
		// 取vkey值   end
		//得到平台的返回数据
		String response = super.doGet(querySongUrl.toString(), null,requestProperty);
		// 验证返回值Code是否正确
		String resultCode = JSONObject.fromObject(response).getString("code");
		// 处理返回数据
		JSONArray dataArray = JSONObject.fromObject(response).getJSONArray("data");
		String orgAlbumId = dataArray.getJSONObject(0).getJSONObject("album").getString("id");
		if (Integer.valueOf(resultCode) != 0) {
			json = bulidJson("9999", null, "API_Code Error",0);
			return json.toString();
		}
		// 处理返回数据
		String songUrl = "https://dl.stream.qqmusic.qq.com/M800"+fileMId+".mp3?vkey="+vkey+"&guid="+guid+"&uid=0&fromtag=30";// 歌曲路径
        // 专辑图片路径
		int num = (int)Double.parseDouble(orgAlbumId)%100; 											// 图片路径要拼接的参数
		String orgPicUrl = "http://imgcache.qq.com/music/photo/album_300/" + num + "/300_albumpic_" + orgAlbumId + "_0.jpg";
		// 处理Array数组
		JSONArray dataAll = new JSONArray();
		
		// 存入 单条数据数组 start ---------------------------------------------------------
		JSONObject objectall = new JSONObject();
        objectall.put("orgSongUrl", songUrl);									// 歌曲路径
        objectall.put("orgPicUrl", orgPicUrl);									// 专辑路径
        objectall.put("source", "0001");										// 所属平台
        objectall.put("orgCode", resultCode);									// 状态码
        // 存入 单条数据数组 end ------------------------------------------------------------
        
        dataAll.add(objectall);
		// 返回结果
		json = bulidJson(resultCode, dataAll, "QQ_Music Deatil Success",1);
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
		searchMusicUrl.append(url+"/soso/fcgi-bin/client_search_cp?");
		// 页码 与 记录数  空的时候 给默认值
		String page = (jsonData.getString("orgPage").equals(""))?("1"):(jsonData.getString("orgPage"));
		String pageSize = (jsonData.getString("orgPageSize").equals(""))?("30"):(jsonData.getString("orgPageSize"));
		searchMusicUrl.append("p=" + page);
		searchMusicUrl.append("&n=" + pageSize);
		searchMusicUrl.append("&w=" + jsonData.getString("orgKeyWord"));
		searchMusicUrl.append("&lossless=1");
		searchMusicUrl.append("&cr=1");
		searchMusicUrl.append("&new_json=1");
		
		// 取vkey值   start
		// 1.产生guid随机值
		String guid = IdGeneratorUtil.getId().substring(0,10);
		// 2.拼接key的url地址
		StringBuilder keyUrl = new StringBuilder();
		keyUrl.append(url+"/base/fcgi-bin/fcg_musicexpress.fcg?");
		keyUrl.append("json=3");
		keyUrl.append("&guid="+guid);
		keyUrl.append("&format=json");
		// 3.获取vkey
		String vkey = super.doGet(keyUrl.toString(), null, requestProperty);
		vkey = JSONObject.fromObject(vkey).getString("key");
		// 取vkey值   end
		
		//得到平台的返回数据
		String response = super.doGet(searchMusicUrl.toString(), null,requestProperty).replace("callback(", "").replace(")", "");
		
		// 验证返回值Code是否正确
		String ResultCode = JSONObject.fromObject(response).getString("code");
		if (Integer.valueOf(ResultCode) != 0) {
			json = bulidJson("9999", null, "API_Code Error",0);
			return json.toString();
		}
		// 处理返回数据
		JSONObject jsonObjectData = JSONObject.fromObject(response).getJSONObject("data").getJSONObject("song");
		String respData = jsonObjectData.getString("list");
		JSONArray jsonObject = JSONArray.fromObject(respData);
		
		//搜索总条数
		int queryCount = jsonObject.size();
		
		// 返回Array数组
		JSONArray dataAll = new JSONArray();
		// 循环遍历出数据 json数组
		for (int i = 0; i < jsonObject.size(); i++) {
			// 单条数据
            JSONObject jsonObjects = jsonObject.getJSONObject(i);
            // 单条数据数组
            JSONObject objectall = new JSONObject();

            // 获取Album专辑部分 start ---------------------------------------
            JSONObject albumObject = jsonObjects.getJSONObject("album");
            String orgAlbumMId = albumObject.getString("mid");
            String orgAlbumId = albumObject.getString("id");
            String orgAlbumName = albumObject.getString("name");
            // 获取Album专辑部分 end -----------------------------------------
            
            
            // 获取File音频部分 start ----------------------------------------
            JSONObject fileObject = jsonObjects.getJSONObject("file");
            String orgFileMId = fileObject.getString("media_mid");
            // 获取File音频部分 end ------------------------------------------
            
            // 获取Singer歌手部分 start ---------------------------------------
            JSONArray singerArray = jsonObjects.getJSONArray("singer");
            String[] orgSingerMId = new String[singerArray.size()];
            String[] orgSingerId = new String[singerArray.size()];
            String[] orgSingerName = new String[singerArray.size()];
            for (int j = 0; j < singerArray.size(); j++) {
            	JSONObject singerObject = singerArray.getJSONObject(j);
 	           	orgSingerMId[j] = singerObject.getString("mid");
 	            orgSingerId[j] = singerObject.getString("id");
 	            orgSingerName[j] = singerObject.getString("name");
			}
            // 获取Singer歌手部分 end   ---------------------------------------
            
            // 获取歌曲播放
            String songUrl = "https://dl.stream.qqmusic.qq.com/M800"+orgFileMId+".mp3?vkey="+vkey+"&guid="+guid+"&uid=0&fromtag=30";// 歌曲路径
            int num = (int)Double.parseDouble(orgAlbumId)%100; 											// 图片路径要拼接的参数
            String orgPicUrl =  "http://imgcache.qq.com/music/photo/album_300/" + num + "/300_albumpic_" + orgAlbumId + "_0.jpg";//专辑路径
            // 歌曲名
            String songName = jsonObjects.getString("name");
            // 判断歌源是否有效
            if("".equals(songUrl)){
            	songName += "(蜡笔小鑫正在努力争取版权)";
            }
            // 存入 单条数据数组      start  -------------------------------------------------------------
			objectall.put("orgAlbumMId", orgAlbumMId);								// 专辑MID
			objectall.put("orgAlbumId", orgAlbumId);								// 专辑ID
			objectall.put("orgAlbumName", orgAlbumName);							// 专辑名
			objectall.put("orgFileMId", orgFileMId);								// 音频MID
			objectall.put("orgSongTitle", songName);								// 歌曲名称
			objectall.put("orgSongTime", jsonObjects.getString("time_public"));		// 歌曲发布时间
			objectall.put("orgSingerName", orgSingerName);							// 歌手名字
			objectall.put("orgSingerId", orgSingerId);								// 歌手ID
			objectall.put("orgSingerMId", orgSingerMId);							// 歌手MID
			objectall.put("orgCode", ResultCode);									// 状态码
			objectall.put("orgSongUrl", songUrl);									// 歌曲路径
			objectall.put("orgPicUrl", orgPicUrl);									// 专辑头像路径
			objectall.put("source", "0001");										// 平台ID
			// 存入 单条数据数组     end  -----------------------------------------------------------------
			
			// 小数组加进大数组
            dataAll.add(objectall);
        }
		// 返回结果
		json = bulidJson(ResultCode, dataAll, "QQ_Music Search Success",queryCount);
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
