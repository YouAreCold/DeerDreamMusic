package com.xky.roll.music_api.util.http;

import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;  
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;  
import com.google.gson.JsonElement;  
import com.google.gson.JsonObject;  
import com.google.gson.JsonParser;

public class JsonUtil {
	
	/** 
     * 获取JsonObject 
     * @param json 
     * @return 
     */  
    public static JsonObject parseJson(String json){  
        JsonParser parser = new JsonParser();  
        JsonObject jsonObj = parser.parse(json).getAsJsonObject();  
        return jsonObj;  
    }  
      
    /** 
     * 根据json字符串返回Map对象 
     * @param json 
     * @return 
     */  
    public static Map<String,Object> toMap(String json){  
        return JsonUtil.toMap(JsonUtil.parseJson(json));  
    }  
      
    /** 
     * 将JSONObjec对象转换成Map-List集合 
     * @param json 
     * @return 
     */  
    public static Map<String, Object> toMap(JsonObject json){  
        Map<String, Object> map = new HashMap<String, Object>();  
        Set<Entry<String, JsonElement>> entrySet = json.entrySet();  
        for (Iterator<Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ){  
            Entry<String, JsonElement> entry = iter.next();  
            String key = entry.getKey();  
            Object value = entry.getValue();  
            if(value instanceof JsonArray)  
                map.put((String) key, toList((JsonArray) value));  
            else if(value instanceof JsonObject)  
                map.put((String) key, toMap((JsonObject) value));  
            else  
                map.put((String) key, value);  
        }  
        return map;  
    }  
      
    /** 
     * 将JSONArray对象转换成List集合 
     * @param json 
     * @return 
     */  
    public static List<Object> toList(JsonArray json){  
        List<Object> list = new ArrayList<Object>();  
        for (int i=0; i<json.size(); i++){  
            Object value = json.get(i);  
            if(value instanceof JsonArray){  
                list.add(toList((JsonArray) value));  
            }  
            else if(value instanceof JsonObject){  
                list.add(toMap((JsonObject) value));  
            }  
            else{  
                list.add(value);  
            }  
        }  
        return list;  
    }  
    		//将Json数据解析成相应的映射对象
         public static <T> T toJavaBean(String jsonData, Class<T> type) {
             Gson gson = new Gson();
             T result = gson.fromJson(jsonData, type);
             return result;
         }

}
