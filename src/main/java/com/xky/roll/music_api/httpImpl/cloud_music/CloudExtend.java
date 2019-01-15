package com.xky.roll.music_api.httpImpl.cloud_music;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.xky.roll.music_api.util.IdGenerator.IdGeneratorUtil;
import com.xky.roll.music_api.util.http.HttpUtil;

/**
 * 网易云 api实现类(未开发)
 * 
 * @author wjx
 *
 */
public class CloudExtend extends HttpUtil {

	/**
	 * 重写父类doPost方法
	 * @param url
	 */
	@Override
	public String startInvoke(String url,Object param,Map<String, String> headMap) {
		// 地址栏 POST参数 入参  
		JSONObject jsonData = JSONObject.fromObject(param);
		// 处理返回对应的功能号
		switch (jsonData.getString("restMethod")) {
			case "searchMusic":
				return searchMusic(jsonData, url, param);//查询歌曲搜索列表
			case "querySong":
				return querySong(jsonData, url, param);// 查询歌曲详情
			case "queryLyric":
				return queryLyric(jsonData, url, param);// 查询歌词详情
			case "userInfo":
				return userInfo(jsonData, url, param);// 查询用户歌单
			case "userInfoPlayList":
				return userInfoPlayList(jsonData, url, param);// 读取用户歌单
			default:
				return bulidJson("-1", null, "请选择正确的平台功能号！", 0).toString();// 没找到对应的功能号
		}
	}
	
	// 音乐平台 功能方法模块  start -------------------------------------------------------------------------------------------
	
	/**
	 * 查询歌词详情
	 * @author wjx
	 */
	private String queryLyric(JSONObject jsonData,String url, Object param ) {

		JSONObject json = new JSONObject();
		String fileMId = jsonData.getString("orgFileMId");
		//http://music.163.com/api/song/lyric?id=490595927&os=-1&lv=-1&kv=-1&tv=-1
		// 拼接url 
		StringBuilder queryLyricUrl = new StringBuilder();
		queryLyricUrl.append(url + "/api/song/lyric?");
		queryLyricUrl.append("id="+fileMId);
		queryLyricUrl.append("&os=-1");
		queryLyricUrl.append("&lv=-1");
		queryLyricUrl.append("&kv=-1");
		queryLyricUrl.append("&tv=-1");

		String response = super.startInvoke(queryLyricUrl.toString(),"",null).replace("\n", "");
		
		String lyric = JSONObject.fromObject(response).getJSONObject("lrc").getString("lyric");
		
		// 处理Array数组
		JSONArray dataAll = new JSONArray();
		
		// 存入 单条数据数组 start ---------------------------------------------------------
		JSONObject objectall = new JSONObject();
        objectall.put("orgLyric", lyric);									// 歌词
        objectall.put("orgCode", "0");										// 状态码
        objectall.put("source", "0002");									// 所属平台
        // 存入 单条数据数组 end ------------------------------------------------------------
        
        dataAll.add(objectall);
		// 返回结果
		json = bulidJson("0", dataAll, "Cloud_Music Lyric Success",1);
		return json.toString();
	}

	/**
	 * 查询歌曲详情 
	 * @author wjx
	 */
	private String querySong(JSONObject jsonData,String url, Object param ) {
		JSONObject json = new JSONObject();
		//get参数
		StringBuilder querySongUrl = new StringBuilder();
		querySongUrl.append(url + "/api/song/enhance/player/url?");
		// 音频ID
		String fileMId = jsonData.getString("orgFileMId");
		querySongUrl.append("ids=['" + fileMId + "']");
		querySongUrl.append("&br=320000");
		Map<String, String> headMap = new HashMap<String, String>();
		
		headMap.put("Host", "music.163.com");
		headMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0");
		headMap.put("Accept", "*/*");
		headMap.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
		headMap.put("Accept-Encoding", "gzip, deflate");
		headMap.put("Content-Type", "text/plain;charset=UTF-8");
		headMap.put("Connection", "keep-alive");
		
		// 设置请求头
		String response = super.startInvoke(querySongUrl.toString(),"",headMap);
		
		JSONArray jsonRespData = JSONObject.fromObject(response).getJSONArray("data");
		String songUrl = jsonRespData.getJSONObject(0).getString("url");
		
		// 获取专辑图片 start ------------------------------------------------------
		StringBuilder imgUrl = new StringBuilder();
		imgUrl.append(url + "/api/v3/song/detail?");
		// 音频ID
		imgUrl.append("c=[{id:"+fileMId+"}]");
		String ImgUrlresponse = super.startInvoke(imgUrl.toString().replace("{", "%7b").replace("}", "%7d"),"",headMap);
		String orgPicUrl = JSONObject.fromObject(ImgUrlresponse).getJSONArray("songs").getJSONObject(0).getJSONObject("al").getString("picUrl");
		if("".equals(orgPicUrl)){
    		orgPicUrl = "http://127.0.0.1:8089/DeerDreamMusic/DeerDreamMusic-View/images/other-images.jpg";
    	}
		// 获取专辑图片 end --------------------------------------------------------
		// 处理Array数组
		JSONArray dataAll = new JSONArray();
		
		// 存入 单条数据数组 start ---------------------------------------------------------
		JSONObject objectall = new JSONObject();
        objectall.put("orgSongUrl", songUrl);									// 歌曲路径
        objectall.put("orgPicUrl", orgPicUrl);									// 专辑图片路径
        objectall.put("source", "0002");										// 所属平台
        objectall.put("orgCode", 0);											// 状态码
        // 存入 单条数据数组 end ------------------------------------------------------------
        
        dataAll.add(objectall);
		// 返回结果
		json = bulidJson("0", dataAll, "Cloud_Music Deatil Success",1);
		return json.toString();
	}
	
	/**
	 * 查询歌曲搜索列表 
	 * @author wjx
	 */
	private String searchMusic(JSONObject jsonData,String url, Object param) {
		//get参数
		Integer page = Integer.parseInt((jsonData.getString("orgPage").equals(""))?("1"):(jsonData.getString("orgPage")));
		Integer pageSize = Integer.parseInt((jsonData.getString("orgPageSize").equals(""))?("30"):(jsonData.getString("orgPageSize")));
		// 拼接url 
		StringBuilder searchMusicUrl = new StringBuilder();
		searchMusicUrl.append(url + "/api/cloudsearch/pc?");
		searchMusicUrl.append("total=true");
		searchMusicUrl.append("&s=" + jsonData.getString("orgKeyWord"));
		searchMusicUrl.append("&offset=" + (page-1)*pageSize);
		searchMusicUrl.append("&limit=" + pageSize);
		searchMusicUrl.append("&type=1");
		
		// 设置请求头
		String response = super.startInvoke(searchMusicUrl.toString(),"",null);
		
		JSONArray jsonRespData = JSONObject.fromObject(response).getJSONObject("result").getJSONArray("songs");
		//搜索总条数
		int queryCount = jsonRespData.size();

		// 返回Array数组
		JSONArray dataAll = new JSONArray();
		
		// 循环遍历出数据 json数组
		for (int i = 0; i < jsonRespData.size(); i++) {
			// 单条数据
	        JSONObject jsonObjects = jsonRespData.getJSONObject(i);
	        // 单条数据数组
	        JSONObject objectall = new JSONObject();
	        
	        // 获取ar歌手部分 start ---------------------------------------
	        JSONArray singerArray = jsonObjects.getJSONArray("ar");
	        String[] orgSingerId = new String[singerArray.size()];
	        String[] orgSingerName = new String[singerArray.size()];
	        for (int j = 0; j < singerArray.size(); j++) {
	        	JSONObject singerObject = singerArray.getJSONObject(j);
		            orgSingerId[j] = singerObject.getString("id");
		            orgSingerName[j] = singerObject.getString("name");
			}
	        // 获取ar歌手部分 end   ---------------------------------------
	        
	        // 获取al专辑部分 start ---------------------------------------
	        JSONObject alArray = jsonObjects.getJSONObject("al");
	        String orgAlbumId = alArray.getString("id");
	        String orgAlbumName = alArray.getString("name");
	        String orgPicUrl = alArray.getString("picUrl");
	        if("".equals(orgPicUrl)){
	    		orgPicUrl = "http://127.0.0.1:8089/DeerDreamMusic/DeerDreamMusic-View/images/other-images.jpg";
	    	}
	        // 获取al专辑部分 end   ---------------------------------------
	        
	        // 获取歌曲路径部分  start -----------------------------------------
	        StringBuilder querySongUrl = new StringBuilder();
			querySongUrl.append(url + "/api/song/enhance/player/url?");
			// 音频ID
			querySongUrl.append("ids=['" + jsonObjects.getString("id") + "']");
			querySongUrl.append("&br=320000");
			Map<String, String> headMap = new HashMap<String, String>();
			
			headMap.put("Host", "music.163.com");
			headMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0");
			headMap.put("Accept", "*/*");
			headMap.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
			headMap.put("Accept-Encoding", "gzip, deflate");
			headMap.put("Content-Type", "text/plain;charset=UTF-8");
			headMap.put("Connection", "keep-alive");
			
			// 设置请求头
			String responseSongUrl = super.startInvoke(querySongUrl.toString(),"",headMap);
			
			JSONArray songDescData = JSONObject.fromObject(responseSongUrl).getJSONArray("data");
			String songUrl = songDescData.getJSONObject(0).getString("url");
	        // 获取歌曲路径部分  end--------------------------------------------
			
			// 歌曲名
            String songName = jsonObjects.getString("name");
            // 查询不到歌源的提示已下架
            if("".equals(songUrl)|| songUrl.indexOf("403") != -1){
            	songName += "(蜡笔小鑫正在努力争取版权)";
            }
	        // 存入 单条数据数组      start  -------------------------------------------------------------
			objectall.put("orgAlbumId", orgAlbumId);										// 专辑ID
			objectall.put("orgAlbumName", orgAlbumName);									// 专辑名
			objectall.put("orgFileMId", jsonObjects.getString("id"));						// 音频MID
			objectall.put("orgSongTitle", songName);										// 歌曲名称
			objectall.put("orgSongTime", jsonObjects.getString("publishTime"));				// 歌曲发布时间
			objectall.put("orgSingerName", orgSingerName);									// 歌手名字
			objectall.put("orgSingerId", orgSingerId);										// 歌手ID
			objectall.put("orgSongUrl", songUrl);											// 歌曲路径
			objectall.put("orgPicUrl", orgPicUrl);											// 专辑头像路径
			objectall.put("source", "0002");												// 平台ID
			// 存入 单条数据数组     end  -----------------------------------------------------------------
			
			// 小数组加进大数组
	        dataAll.add(objectall);
		}
		// 返回结果
		return bulidJson("0", dataAll, "Cloud_Music Search Success",queryCount).toString();
		
	}
	
	/**
	 *  查询用户歌单
	 * @param jsonData
	 */
	private String userInfo(JSONObject jsonData,String url, Object param) {
		//get参数
		// 拼接url 
		StringBuilder userInfocUrl = new StringBuilder();
		userInfocUrl.append(url + "/api/user/playlist?");
		userInfocUrl.append("offset=0");
		userInfocUrl.append("&limit=1001");
		userInfocUrl.append("&uid="+jsonData.getString("orgUserId"));
		
		// 设置请求头
		Map<String, String> headMap = new HashMap<String, String>();
		
		headMap.put("Host", "music.163.com");
		headMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0");
		headMap.put("Accept", "*/*");
		headMap.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
		headMap.put("Accept-Encoding", "gzip, deflate");
		headMap.put("Content-Type", "text/plain;charset=UTF-8");
		headMap.put("Connection", "keep-alive");
		
		// 设置请求头
		String response = super.startInvoke(userInfocUrl.toString(),"",headMap);
		
		JSONArray playlistArray = JSONObject.fromObject(response).getJSONArray("playlist");
		//搜索总条数(喜欢的歌单以及收藏的歌单)
		int queryCount = playlistArray.size();

		// 返回Array数组
		JSONArray dataAll = new JSONArray();
		
		// 循环遍历出数据 json数组
		for (int i = 0; i < playlistArray.size(); i++) {
			// 单条数据
	        JSONObject jsonObjects = playlistArray.getJSONObject(i);
	        // 单条数据数组
	        JSONObject objectall = new JSONObject();
	        JSONObject userInfoMe = jsonObjects.getJSONObject("creator");
	        // 存入 单条数据数组      start  -------------------------------------------------------------
			objectall.put("tags", jsonObjects.getString("tags"));						// 标签
			objectall.put("coverImgUrl", jsonObjects.getString("coverImgUrl"));			// 歌单图片路径
			objectall.put("playCount", jsonObjects.getString("playCount"));				// 播放数量
			objectall.put("userId", jsonObjects.getString("userId"));					// 用户ID
			objectall.put("updateTime", jsonObjects.getString("updateTime"));			// 更改歌单时间
			objectall.put("description", jsonObjects.getString("description"));			// 歌单简介
			objectall.put("name", jsonObjects.getString("name"));						// 歌单名字
			objectall.put("id", jsonObjects.getString("id"));							// 歌单ID
			objectall.put("source", "0002");											// 平台ID
			objectall.put("avatarUrl",userInfoMe.getString("avatarUrl"));				// 用户头像							// 平台ID
			objectall.put("nickname",userInfoMe.getString("nickname"));					// 用户昵称
			objectall.put("userType", userInfoMe.getString("userType"));				// 性别
			objectall.put("backgroundUrl", userInfoMe.getString("backgroundUrl"));		// 背景图片
			// 存入 单条数据数组     end  -----------------------------------------------------------------
			
			// 小数组加进大数组
	        dataAll.add(objectall);
		}
		// 返回结果
		return bulidJson("0", dataAll, "Cloud_Music UserInfo Success",queryCount).toString();
	
		
	}

	
	/**
	 *  读取歌单
	 * @param jsonData
	 */
	private String userInfoPlayList(JSONObject jsonData,String url, Object param) {

		JSONObject json = new JSONObject();
		//get参数
		StringBuilder userInfoPlayUrl = new StringBuilder();
		userInfoPlayUrl.append(url + "/api/v3/playlist/detail?");
		userInfoPlayUrl.append("s=0");
		userInfoPlayUrl.append("&id=" + jsonData.getString("pId"));
		userInfoPlayUrl.append("&n=1000");
		userInfoPlayUrl.append("&t=0");
		
		Map<String, String> headMap = new HashMap<String, String>();
		
		headMap.put("Host", "music.163.com");
		headMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0");
		headMap.put("Accept", "*/*");
		headMap.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
		headMap.put("Accept-Encoding", "gzip, deflate");
		headMap.put("Content-Type", "text/plain;charset=UTF-8");
		headMap.put("Connection", "keep-alive");
		
		// 设置请求头
		String response = super.startInvoke(userInfoPlayUrl.toString(),"",headMap);
		// 该歌单对应的用户信息
		JSONObject userInfoObj = JSONObject.fromObject(response).getJSONObject("playlist").getJSONObject("creator");
		// 该歌单的基本信息
		JSONObject coverInfoObj = JSONObject.fromObject(response).getJSONObject("playlist");
		JSONArray jsonRespData = JSONObject.fromObject(response).getJSONObject("playlist").getJSONArray("tracks");
		// 返回Array数组
		JSONArray dataAll = new JSONArray();
		// 循环遍历出数据 json数组
		for (int i = 0; i < jsonRespData.size(); i++) {
			JSONObject jsonObjects = jsonRespData.getJSONObject(i);
			
			// 获取歌手信息  start
			JSONArray arJsonObj = jsonObjects.getJSONArray("ar");
			String[] singerName = new String[arJsonObj.size()];// 歌手名字
			String[] singerId = new String[arJsonObj.size()];// 歌手ID
			for (int j = 0; j < arJsonObj.size(); j++) {
				JSONObject arObjs = arJsonObj.getJSONObject(j);
				singerName[j] = arObjs.getString("name");
				singerId[j] = arObjs.getString("id");
			}
			// 获取歌手信息  end 
			
			// 获取专辑信息  start
			JSONObject alJsonObj = jsonObjects.getJSONObject("al");
			String alId = alJsonObj.getString("id");// 专辑ID
			String alName = alJsonObj.getString("name");//专辑名称
			String picUrl = alJsonObj.getString("picUrl");//专辑图片路径
			
			// 获取专辑信息  end 
			
	        // 单条数据数组
	        JSONObject objectall = new JSONObject();
	        // 存入 单条数据数组      start  -------------------------------------------------------------
			objectall.put("name", jsonObjects.getString("name"));					// 歌曲名
			objectall.put("id", jsonObjects.getString("id"));						// 歌曲id
			objectall.put("singerName", singerName);								// 歌手名字
			objectall.put("alId", alId);											// 专辑ID
			objectall.put("alName", alName);										// 专辑名称
			objectall.put("picUrl", picUrl);										// 专辑图片路径
			objectall.put("publishTime", jsonObjects.getString("publishTime"));		// 歌曲发布时间
			objectall.put("avatarUrl", userInfoObj.getString("avatarUrl"));			// 用户头像
			objectall.put("userId", userInfoObj.getString("userId"));				// 用户ID
			objectall.put("nickname", userInfoObj.getString("nickname"));			// 用昵称
			objectall.put("signature", userInfoObj.getString("signature"));			// 用户简介
			objectall.put("backgroundUrl", userInfoObj.getString("backgroundUrl"));	// 背景图片
			objectall.put("userType", userInfoObj.getString("userType"));			// 性别
			objectall.put("coverImgUrl", coverInfoObj.getString("coverImgUrl"));// 歌单封面
			objectall.put("coverName", coverInfoObj.getString("name"));// 歌单名字
			// 存入 单条数据数组     end  -----------------------------------------------------------------
			
			// 小数组加进大数组
	        dataAll.add(objectall);
		}	        
		
		// 返回结果
		json = bulidJson("0", dataAll, "Cloud_Music UserInfoPlayList Success",1);
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
