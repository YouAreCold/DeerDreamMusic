package com.xky.roll.music_api.httpImpl.kugou_music;

import java.util.Date;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.xky.roll.music_api.util.IdGenerator.IdGeneratorUtil;
import com.xky.roll.music_api.util.http.HttpUtil;
import com.xky.roll.music_service.util.DateUtil;

/**
 * 网易云 api实现类(未开发)
 * 
 * @author wjx
 *
 */
public class KuGouExtend extends HttpUtil {

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
			default:
				return bulidJson("-1", null, "请选择正确的平台功能号！", 0).toString();// 没找到对应的功能号
		}
	}
	
	// 音乐平台 功能方法模块  start -------------------------------------------------------------------------------------------
	
	/**
	 * 查询歌词详情
	 * @author wjx
	 */
	private String queryLyric(JSONObject jsonData,String url, Object param) {
		JSONObject json = new JSONObject();
		String fileMId = jsonData.getString("orgFileMId");
		//get参数
		StringBuilder queryLyricUrl = new StringBuilder();
		queryLyricUrl.append("http://m.kugou.com/app/i/krc.php?");
		// 页码 与 记录数  空的时候 给默认值
		queryLyricUrl.append("timelength=1000000");
		queryLyricUrl.append("&cmd=100");
		queryLyricUrl.append("&hash="+fileMId);
		
		//得到平台的返回数据
		String lyric = super.doGet(queryLyricUrl.toString(), null,"");
		
		// 处理Array数组
		JSONArray dataAll = new JSONArray();
		
		// 存入 单条数据数组 start ---------------------------------------------------------
		JSONObject objectall = new JSONObject();
        objectall.put("orgLyric", lyric);									// 歌词
        objectall.put("orgCode", 0);										// 状态码
        objectall.put("source", "0003");									// 所属平台
        // 存入 单条数据数组 end ------------------------------------------------------------
        
        dataAll.add(objectall);
		// 返回结果
		json = bulidJson("0", dataAll, "KuGou_Music Lyric Success",1);
		return json.toString();
	}

	/**
	 * 查询歌曲详情 
	 * @author wjx
	 */
	private String querySong(JSONObject jsonData,String url, Object param) {

		JSONObject json = new JSONObject();
		// 音频ID
		String fileMId = jsonData.getString("orgFileMId");
		 // 歌曲详情查询部分  start-----------------------------------------
        StringBuilder descMusicUrl = new StringBuilder();
        descMusicUrl.append("http://m.kugou.com/app/i/getSongInfo.php?");
		// 页码 与 记录数  空的时候 给默认值
		descMusicUrl.append("cmd=playInfo");
		descMusicUrl.append("&from=mkugou");
		descMusicUrl.append("&hash=" + fileMId);
        String responseDesc = super.startInvoke(descMusicUrl.toString(),"",null).replace("{size}", "400");
    	String orgPicUrl = JSONObject.fromObject(responseDesc).getString("imgUrl");
    	if("".equals(orgPicUrl)){
    		orgPicUrl = "http://127.0.0.1:8089/DeerDreamMusic/DeerDreamMusic-View/images/other-images.jpg";
    	}
    	String orgSongUrl = JSONObject.fromObject(responseDesc).getString("url");
        // 歌曲详情查询部分  end-----------------------------------------
    	
    	// 处理Array数组
		JSONArray dataAll = new JSONArray();
		
		// 存入 单条数据数组 start ---------------------------------------------------------
		JSONObject objectall = new JSONObject();
        objectall.put("orgSongUrl", orgSongUrl);									// 歌曲路径
        objectall.put("orgPicUrl", orgPicUrl);									// 专辑图片路径
        objectall.put("source", "0003");										// 所属平台
        objectall.put("orgCode", 0);											// 状态码
        // 存入 单条数据数组 end ------------------------------------------------------------
        
        dataAll.add(objectall);
		// 返回结果
		json = bulidJson("0", dataAll, "KuGou_Music Deatil Success",1);
		return json.toString();
	
		
	}
	
	/**
	 * 查询歌曲搜索列表 
	 * @author wjx
	 */
	private String searchMusic(JSONObject jsonData,String url, Object param) {
		// 最终返回的jsonobject对象
		JSONObject json = new JSONObject();
		//get参数
		StringBuilder searchMusicUrl = new StringBuilder();
		searchMusicUrl.append(url+"/api/v3/search/song?");
		// 页码 与 记录数  空的时候 给默认值
		String page = (jsonData.getString("orgPage").equals(""))?("1"):(jsonData.getString("orgPage"));
		String pageSize = (jsonData.getString("orgPageSize").equals(""))?("30"):(jsonData.getString("orgPageSize"));
		searchMusicUrl.append("iscorrect=1");
		searchMusicUrl.append("&pagesize=" + pageSize);
		searchMusicUrl.append("&plat=2");
		searchMusicUrl.append("&tag=1");
		searchMusicUrl.append("&sver=5");
		searchMusicUrl.append("&showtype=10");
		searchMusicUrl.append("&page=" + page);
		searchMusicUrl.append("&keyword=" + jsonData.getString("orgKeyWord"));
		searchMusicUrl.append("&version=8550");
		
		//得到平台的返回数据
		String response = super.doGet(searchMusicUrl.toString(), null,"");
		
		JSONArray jsonArray = JSONObject.fromObject(response).getJSONObject("data").getJSONArray("info");
		
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
	        
	        // 歌曲详情查询部分  start-----------------------------------------
	        StringBuilder descMusicUrl = new StringBuilder();
	        descMusicUrl.append("http://m.kugou.com/app/i/getSongInfo.php?");
			// 页码 与 记录数  空的时候 给默认值
			descMusicUrl.append("cmd=playInfo");
			descMusicUrl.append("&from=mkugou");
			descMusicUrl.append("&hash="+jsonObjects.getString("hash"));
	        String responseDesc = super.startInvoke(descMusicUrl.toString(),"",null).replace("{size}", "400");
	    	String orgPicUrl = JSONObject.fromObject(responseDesc).getString("imgUrl");
	    	if("".equals(orgPicUrl)){
	    		orgPicUrl = "http://127.0.0.1:8089/DeerDreamMusic/DeerDreamMusic-View/images/other-images.jpg";
	    	}
	    	String orgSongUrl = JSONObject.fromObject(responseDesc).getString("url");
	        // 歌曲详情查询部分  end-----------------------------------------
	    	
	    	// 歌曲名
            String songName = jsonObjects.getString("songname");
            // 查询不到歌源的提示已下架
            if("".equals(orgSongUrl)|| orgSongUrl.indexOf("403") != -1){
            	songName += "(蜡笔小鑫正在努力争取版权)";
            }
	        // 存入 单条数据数组      start  -------------------------------------------------------------
			objectall.put("orgAlbumId", jsonObjects.getString("album_id"));								// 专辑ID
			objectall.put("orgAlbumName", jsonObjects.getString("album_name"));							// 专辑名
			objectall.put("orgFileMId", jsonObjects.getString("hash"));									// 音频MID
			objectall.put("orgSongTitle", songName);													// 歌曲名称
			objectall.put("orgSongTime", DateUtil.formatDate(new Date()));								// 歌曲发布时间
			String[] singernameArray = jsonObjects.getString("singername").split("、");
			objectall.put("orgSingerName", singernameArray);											// 歌手名字
			objectall.put("orgSongUrl", orgSongUrl);													// 歌曲路径
			objectall.put("orgPicUrl", orgPicUrl);														// 专辑头像路径
			objectall.put("source", "0003");															// 平台ID
			// 存入 单条数据数组     end  -----------------------------------------------------------------
			
			// 小数组加进大数组
	        dataAll.add(objectall);
		}
		json = bulidJson("0", dataAll, "KuGou_Music Search Success",queryCount);
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
