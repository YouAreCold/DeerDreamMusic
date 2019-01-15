/**   
* @Title: StringUtil.java 
* @Package com.bd.plugin.hisESB.util 
* @Description: 字符串处理工具类 
* @author wangzp   
* @date 2015-8-14 下午5:53:33 
* @version V1.0  
* <p>
* Department : 研发中心
* </p>
* </p>
* Copyright : 2015-2016 深圳市宁远科技有限公司 
*</p> 
*/
package com.xky.roll.music_service.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.druid.util.StringUtils;


/** 
 * @ClassName: StringUtil 
 * @Description: 字符串处理工具类
 * @author wangzp
 * @date 2015-8-14 下午5:53:33   
 */


public class StringUtil {

	public static String checkWebPath(String webPath) {
		String first = webPath.substring(0, 1);
		if (!"/".equals(first)) {
			webPath = "/" + webPath;
		}
		String last = webPath.substring(webPath.length() - 1, webPath.length());
		if (!"/".equals(last)) {
			webPath = webPath + "/";
		}
		return webPath;
	}
	
	/**
	 * 验证字符串是否为空
	 * 
	 * @param str
	 *            字符串
	 * @return true 空 false 非空
	 */
	public static boolean isEmpty(String str) {
		if (null == str) {
			return true;
		} else if ("".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 验证字符串是否为空
	 * 
	 * @param str
	 *            字符串
	 * @return true 空 false 非空
	 */
	public static boolean isNotEmpty(String str) {
		if (null == str) {
			return false;
		} else if ("".equals(str)) {
			return false;
		} else {
			return true;
		}
	}

	public static String NullToEmpty(String str) {
		return str == null ? "" : str;
	}

	public static String convertEncoding(String value) {
		if (!StringUtil.isEmpty(value)) {
			try {
				return new String(value.getBytes("iso-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static String urlEncoding(String str) {
		try {
			if(!StringUtils.isEmpty(str)){
				return URLEncoder.encode(str, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String truncateStr(String str, int len) {
		if(str == null){
			return "";
		}else if(str.length() < len){
			return str;
		}else{
			return str.substring(0, len) + "...";
		}
	}
	
	public static boolean hasEmpty(String... strs){
		if(strs != null && strs.length != 0){
			for(String str : strs){
				if(StringUtils.isEmpty(str)){
					return true;
				}
			}
		}
		return false;
	}
	
	 /**
     * 获取异常的具体信息
     */
    public static String getExceptionMsg(Exception e) {
        StringWriter sw = new StringWriter();
        try{
            e.printStackTrace(new PrintWriter(sw));
        }finally {
            try {
                sw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return sw.getBuffer().toString().replaceAll("\\$","T");
    }
    /**
	 * 替换掉html字符成空
	 * 
	 * @param string 待替换的字符串
	 * @return 替换后的字符串
	 */
	public static String replaceHTMLTag(String string) {
		String temp = string.replaceAll("&lt;", "<").replaceAll("&gt;", ">")
				.replaceAll("&#60;", "<").replaceAll("&#62;", ">");

		return temp;
	}

}
