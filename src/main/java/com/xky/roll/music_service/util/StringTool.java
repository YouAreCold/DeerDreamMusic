/*
* @(#)StringTool.java 1.1 2011
*
*/
package com.xky.roll.music_service.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 公共字符串处理函数
* @author Hiroad
 * @since 07.09.2008
 */
public class StringTool {
	private static Logger logger=Logger.getLogger(StringTool.class);
	public static final String CHAR_CODE = "gbk";
	private static final String strVarExpStart = "[$]{1}[{]{1}[\\s]*";
	private static final String strVarExpEnd ="[\\s]*[}]{1}";
	private static final String[] OPEN_TAG_INVARIATION = new String[] {
		"<",
		"%3C",
		"&lt",
		"&lt;",
		"&LT",
		"&LT;",
		"&0000060",
		"&0000060",
		"&0000060",
		"&0000060",
		"&0000060",
		"&0000060",
		"&0000060;",
		"&0000060;",
		"&0000060;",
		"&0000060;",
		"&0000060;",
		"&0000060;",
		"&#x3c",
		"&#x03c",
		"&#x003c",
		"&#x0003c",
		"&#x00003c",
		"&#x000003c",
		"&#x3c;",
		"&#x03c;",
		"&#x003c;",
		"&#x0003c;",
		"&#x00003c;",
		"&#x000003c;",
		"&#X3c",
		"&#X03c",
		"&#X003c",
		"&#X0003c",
		"&#X00003c",
		"&#X000003c",
		"&#X3c;",
		"&#X03c;",
		"&#X003c;",
		"&#X0003c;",
		"&#X00003c;",
		"&#X000003c;",
		"&#x3C",
		"&#x03C",
		"&#x003C",
		"&#x0003C",
		"&#x00003C",
		"&#x000003C",
		"&#x3C;",
		"&#x03C;",
		"&#x003C;",
		"&#x0003C;",
		"&#x00003C;",
		"&#x000003C;",
		"&#X3C",
		"&#X03C",
		"&#X003C",
		"&#X0003C",
		"&#X00003C",
		"&#X000003C",
		"&#X3C;",
		"&#X03C;",
		"&#X003C;",
		"&#X0003C;",
		"&#X00003C;",
		"&#X000003C;",
		"\\x3c",
		"\\x3C",
		"\u003c",
		"\u003C"
	};

	/**
	 * 转换字符串为方法名称
	 * @param s
	 */
	public static String toMethodName(String s) {
		if (isNull(s))
			return s;

		StringBuffer tname = new StringBuffer();

		boolean begin = true;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch != ' ') {
				if (begin)
					tname.append(Character.toUpperCase(ch));
				else
					tname.append(ch);
				begin = false;
			} else
				begin = true;
		}
		return tname.toString();
	}

	/**
	 * 判断字符串是否为空串
	 * @param s
	 * @return
	 */
	public static boolean isNull(String s) {
		if (s == null  || "null".equals(s.trim()) || "".equals(s.trim()))
			return true;
		return false;
	}

	/**
	 * 增加一些参数到url中
	 * @param url
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String addParamsToUrl(String url, Map params) {
		if (isNull(url) || params == null)
			return url;

		StringBuffer newUrl = new StringBuffer(url);
		for (Iterator it = params.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			if (newUrl.indexOf("?") > 0)
				newUrl.append("&");
			else
				newUrl.append("?");
			newUrl.append(entry.getKey());
			newUrl.append("=");
			newUrl.append(entry.getValue());
		}
		return newUrl.toString();
	}
	
	/**
	 * 增加一个参数到url中
	 * @param url
	 * @param params
	 * @return
	 */
	public static String addParamsToUrl(String url,String name,String value) {
		if (isNull(url) || name == null || "".equals(name.trim()))
			return url;
		if(value == null)
			value="";

		StringBuffer newUrl = new StringBuffer(url);
		if (newUrl.indexOf("?") > 0)
			newUrl.append("&");
		else
			newUrl.append("?");
		newUrl.append(name);
		newUrl.append("=");
		newUrl.append(value);
		return newUrl.toString();
	}
	
	/**
	 * 删除URL中的参数
	 * @param url
	 * @param name
	 * @return
	 */
	public static String removeParamFromUrl(String url,String name){
		if(isNull(url))
			return url;
		StringBuffer newUrl = new StringBuffer();
		int beg=url.indexOf("?");
		if(beg >=0){
			newUrl.append(url.substring(0,beg));
			url=url.substring(beg+1);
		}
		
		boolean begflg=false;
		for(int i=1;;i++){
			String field=StringTool.getField(url, i, "&");
			if(field == null){
				break;
			}
			if(StringTool.isNull(field))
				continue;
			
			String pname=StringTool.getField(field, 1, "=");
			if(pname != null && pname.trim().equals(name)){
				continue;
			}
			if(begflg)
				newUrl.append("&");
			else 
				newUrl.append("?");
			begflg=true;
			newUrl.append(field);
		}
		return newUrl.toString();
	}

	public static String encodeUrl(String s)
			throws UnsupportedEncodingException {
		return URLEncoder.encode(s, CHAR_CODE);
	}

	public static String decodeUrl(String s)
			throws UnsupportedEncodingException {
		return URLDecoder.decode(s, CHAR_CODE);
	}
	
	/**
	 * 取字符串中的自定义变量
	 * @param source
	 * @return
	 */
	public static List<String> findVars(String source){
		List<String>  vars=new ArrayList<String>();
		if(source == null || "".equals(source))
			return vars;
		
		StringBuilder pattern=new StringBuilder(strVarExpStart);
		pattern.append("[a-zA-Z0-9_\\.-]+").append(strVarExpEnd);;
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern.toString(),
				java.util.regex.Pattern.DOTALL);
		java.util.regex.Matcher m = p.matcher(source);

		
		while (m.find()) {
			String result=m.toMatchResult().group();
			vars.add(result.substring(2,result.length()-1).trim());
		}
		return vars;
	}

	
	/**
	 * 替换字符串中的自定义变量的值
	 * @param source
	 * @param var
	 * @param value
	 * @return
	 */
	public static String replaceVar(String source,String var,String value){	
		if(source == null)
			return "";
		if(value==null)
			value="";
		
		int pos=value.indexOf("$");
		if(pos <0){
			pos=value.indexOf("\\");
		}
		if(pos< 0){	
			StringBuilder pattern=new StringBuilder(strVarExpStart);
			pattern.append(var).append(strVarExpEnd);
			java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern.toString(),
				java.util.regex.Pattern.DOTALL);
			java.util.regex.Matcher m = p.matcher(source);
		
			String str=source;
			if(m.find()){
				str=m.replaceAll(value);
			}
			return str;
		}
		else {
			String match="${"+var+"}";
			StringBuilder str=new StringBuilder();
			while(true){
				pos=source.indexOf(match);
				if(pos >= 0){
					str.append(source.substring(0,pos)).append(value);
					source=source.substring(pos+match.length());
				}
				else {
					str.append(source);
					break;
				}
			}
			return str.toString();
		}
	}
	
	/**
	 * 判断字符串相等
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean stringEqual(String str1,String str2){
		str1=(str1 == null)?"":str1;
		str2=(str2 == null)?"":str2;
		return str1.trim().equals(str2.trim());
	}
	
	/**
	 * 替换字符串中指定位置的值
	 * @param source
	 * @param pos
	 * @param repchar
	 * @return
	 */
	public static String setStringChar(String source,int pos,String repchar){
		if(source == null)
			source="";
		if(pos < 0)
			return source;
		
		if(source.length() <= pos)
			return source;
		return source.substring(0,pos)+repchar+source.substring(pos+1);	
	}
	
	/**
	 * 生成随机字符串
	 * @param intLength
	 * @return
	 */
	public static String randomStr( int intLength ) {
	    String strTable = "0123456789ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	    int len = strTable.length();
	    StringBuilder str=new StringBuilder();
	    for ( int i = 0; i < intLength; i++ ) {
	    	double dblR = Math.random() * len;
	    	int intR = (int) Math.floor( dblR );
	    	str.append(strTable.charAt( intR ));
	    }
	    return str.toString();
	}
	
	/**
	 * MD5 
	 * @param s
	 * @param md5Str
	 * @return
	 */
	public static String MD5(String s, String md5Str)
	{ 
	   if (md5Str == null || s==null)
	   {
		   return "";
	   }
	  
	   try {
		   MessageDigest md = MessageDigest.getInstance( "MD5" );
	
		   md.reset();
		   md.update( s.getBytes() );
		   byte[] arr = md.digest();

		   for (int i = 0; i < arr.length; i++) 
		   {
			   if (arr[i]<16 && arr[i]>=0)
			   {
				   md5Str += "0";
				   md5Str += Integer.toHexString(arr[i]&0xff);
			   }
			   else
		   		   md5Str += Integer.toHexString(arr[i]&0xff);
		   }
	   } catch (NoSuchAlgorithmException e) {	   
	   }
   
	   return md5Str;
	}
	
	/**
	 * 取附件文件Key
	 * @return
	 */
	public static String getAttachkey(){
		return StringTool.randomStr(5)+System.currentTimeMillis();
	}
	
	

	
	public static int realLength(String src) {
		char[] fromval = src.toCharArray();
		int len = 0;

		for (int i = 0; i < fromval.length; i++) {
			// 判断是否为汉字字符
			if (java.lang.Character.toString(fromval[i]).matches(
					"[\\u4E00-\\u9FA5]+")) {
				len += 2;
			} else
				len += 1;
		}
		return len;
	}
	

	/**
	 * 将字符串转移为ASCII码
	 * @param cnStr
	 * @return
	 */
	public static String toCnASCII(String cnStr) {
		StringBuffer strBuf = new StringBuffer();
		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
		}
		return strBuf.toString();
	}
	
	/**
	 * 根据分隔符取域
	 * @param data
	 * @param idx
	 * @param split
	 * @return
	 */
	public static String getField(String data,int idx,String split){
		if(data == null || split == null || idx < 1 && idx != -1)
			return null;
		
		int len=split.length();
		if(idx == -1){
			int pos=data.lastIndexOf(split);
			if(pos < 0){
				return data;
			}
			return data.substring(pos+len);
		}
		
		int i=0;
		for(;i<idx;i++){
			int pos=data.indexOf(split);
			if(pos < 0)
				break;
			
			if(i < idx -1){
				data=data.substring(pos+len);
			}
			else {
				return data.substring(0,pos);
			}
		}
		
		if(i < idx-1)
			return null;
		else
			return data;
	}
	
	public final static String upperCaseFirstChar(final String target) {
		if ((target == null) || (target.length() == 0)) {
			return target;
		}
		return Character.toUpperCase(target.charAt(0))
				+ (target.length() > 1 ? target.substring(1) : "");
	}
	

    /**
     * 删除空格
     * 
     * @param s
     * @return
     * @version 1.0
     * @author wolf
     * @update 2012-3-19 下午01:30:51
     */
    public static String trim(String s)
    {
        if (isNull(s)) return "";
        return s.trim();
    }
    
    /**
     * 转换为人民币金额大写
     * @param n
     * @return
     */
    public static String amountToRMB(double n){
    	String fraction[] = {"角", "分"};
    	String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
    	String unit[][] = {{"元", "万", "亿"},
    			           {"", "拾", "佰", "仟"}};

    	String head = n < 0? "负": "";
    	n = Math.abs(n);
 
    	String s = "";
    	for (int i = 0; i < fraction.length; i++) {
    		s += (digit[(int)(Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
    	}
    	if(s.length()<1){
    		s = "整";   
    	}
    	int integerPart = (int)Math.floor(n);
    	
    	for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
    		String p ="";
    		for (int j = 0; j < unit[1].length && n > 0; j++) {
    			p = digit[integerPart%10]+unit[1][j] + p;
    			integerPart = integerPart/10;
    		}
    		s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
    	}
    	return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }
    
    /**
     * 替换中间字符串
     * @param source
     * @param from
     * @param to
     * @return
     */
    public static String replace(String strSource, String strFrom, String strTo) {
		if (StringTool.isNull(strSource) || StringTool.isNull(strFrom))
			return strSource;

		int i = 0;
		if ((i = strSource.indexOf(strFrom, i)) >= 0) {
			char[] cSrc = strSource.toCharArray();
			char[] cTo = strTo.toCharArray();
			int len = strFrom.length();
			StringBuffer buf = new StringBuffer(cSrc.length);
			buf.append(cSrc, 0, i).append(cTo);
			i += len;
			int j = i;
			while ((i = strSource.indexOf(strFrom, i)) > 0) {
				buf.append(cSrc, j, i - j).append(cTo);
				i += len;
				j = i;
			}
			buf.append(cSrc, j, cSrc.length - j);
			return buf.toString();
		}
		return strSource;
	}
    
    /**
     * 取字符串的字串
     * @param strSource
     * @param beg
     * @param end
     * @return
     */
    public static String substring(String strSource,int beg,int end){
    	if(strSource == null)
    		return null;
    	int len=strSource.length();
    	if(beg < 0)
    		beg=len+beg;
    	if(beg >=len || beg<0)
    		return null;
    	
    	if(end < 0)
    		end=len+end;
    	if(end<beg)
    		return null;
    	
    	if(end>len)
    		end=len;
    	return strSource.substring(beg,end);
    }
    
    /**
     * 取字符串的字串
     * @param strSource
     * @param beg
     * @param end
     * @return
     */
    public static String substring(String strSource,int beg){
    	if(strSource == null)
    		return null;
    	int len=strSource.length();
    	if(beg < 0)
    		beg=len+beg;
    	return strSource.substring(beg);
    }
    
    
    /**
     * 清除CSS信息
     * @param value
     * @return
     */
	public static String cleanXSS(String value) {
		if(StringTool.isNull(value))
			return value;
		
		for (String openTagInvariant : OPEN_TAG_INVARIATION) {
			if(value.indexOf(openTagInvariant) >= 0) {
				logger.debug("Found xss attack in parameter : '"+ value + "'");
				value = value.replaceAll(openTagInvariant, "");
			}
		}
		
		value = value.replaceAll("'", "&#39;");
		value = value.replaceAll("\\\"", " ");
		value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");  
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']","\"\"");
		value = value.replaceAll("expression", "");  
		return value;
	} 
}
