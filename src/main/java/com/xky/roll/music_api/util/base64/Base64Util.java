package com.xky.roll.music_api.util.base64;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;  

import com.thoughtworks.xstream.core.util.Base64Encoder;

import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder; 

/**
 * Base64加密解密 工具类
 * @author wjx  
 * 
 */
public class Base64Util {
	
	/**
	 *  加密  
	 * @author wjx
	 */
    public static String getBase64(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new Base64Encoder().encode(b);  
        }  
        return s;  
    }  
  
    /**
     *  解密  
     * @author wjx
     */
    public static String getFromBase64(String s) {  
        byte[] b = null;  
        String result = null;  
        if (s != null) {  
        	BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
                result = new String(b, "utf-8");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  
    }  
}
