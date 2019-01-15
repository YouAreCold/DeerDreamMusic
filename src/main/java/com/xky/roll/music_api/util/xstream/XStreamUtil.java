package com.xky.roll.music_api.util.xstream;

import java.io.Writer;  
import java.util.List;  
  
import com.thoughtworks.xstream.XStream;  
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;  
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;  
import com.thoughtworks.xstream.io.xml.XppDriver; 

/**
 * xml与josn转换工具类
 * @author wjx  
 *
 */
public class XStreamUtil {

	
	/** 
     * Java对象转Xml字符串（序列化） 
     * @param object 
     * @return 
     */  
    public static String bean2Xml(Object object){  
        XStream stream = new XStream(new XppDriver() {  
            public HierarchicalStreamWriter createWriter(Writer out) {  
                return new PrettyPrintWriter(out) {  
  
                    public void startNode(String name) {  
                        // 去掉包名  
                        if (name.indexOf(".") > -1) {  
                            name = name.substring(name.lastIndexOf(".") + 1);  
                        }  
                        super.startNode(name);  
                    };  
                };  
            }  
        });  
        return stream.toXML(object);  
    }  
      
    /** 
     * Xml字符串转Java对象（反序列化） 
     * @param xml 
     * @param rootName 根元素名称 
     * @param rootType 根元素对应的Java类型 
     * @param collectionTypes 集合类型 
     * @return 
     */  
    public static Object xml2Bean(String xml, String rootName, Class<?> rootType,   
            List<Class<?>> collectionTypes){  
        XStream stream = new XStream();  
        stream.alias(rootName, rootType);  
        for (Class<?> clazz : collectionTypes) {  
            stream.alias(clazz.getSimpleName(), clazz);  
        }  
        Object bean = stream.fromXML(xml);  
        return bean;  
    }  
}
