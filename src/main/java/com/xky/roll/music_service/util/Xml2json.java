package com.xky.roll.music_service.util;

import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

/**
 * xml与json转换类 
 * @author wjx  
 *
 */
public class Xml2json {

	public static String ConvertXMLtoJSON(String xml) {
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSON json = xmlSerializer.read(xml);
		JSONObject jsonobject = JSONObject.fromObject(json);
		return jsonobject.get("MsgBody").toString();
	}

	/**
	 * 将xml字符串转换为JSON对象
	 * 
	 * @param xmlFile
	 *            xml字符串
	 * @return JSON对象
	 */
	public static JSON getJSONFromXml(String xmlString) {
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSON json = xmlSerializer.read(xmlString);
		return json;
	}

	/**
	 * 将xml字符串转换为JSON字符串
	 * 
	 * @param xmlString
	 * @return JSON字符串
	 */
	public static String getJSONStringFromXml(String xmlString) {
		return getJSONFromXml(xmlString).toString();
	}

	/**
	 * 将Java对象转换为JSON格式的字符串
	 *
	 * @param javaObj
	 *            POJO,例如日志的model
	 * @return JSON格式的String字符串
	 */
	public static String getJsonStringFromJavaPOJO(Object javaObj) {
		return JSONObject.fromObject(javaObj).toString(1);
	}

	/**
	 * 将Map准换为JSON字符串
	 * 
	 * @param map
	 * @return JSON字符串
	 */
	public static String getJsonStringFromMap(Map<?, ?> map) {
		JSONObject object = JSONObject.fromObject(map);
		return object.toString();
	}

	/**
	 * 将xml带有<name/>结尾的全部转化成<name></name>
	 * 
	 * @param data
	 * @return
	 */
	public static String bulidXML(String data) {
		StringBuffer buffer = new StringBuffer();
		Object[] arge = data.split("/>");
		for (int i = 0; i < arge.length; i++) {
			String node = arge[i].toString().trim();
			if (!node.endsWith(">")) {
				int last = node.lastIndexOf("<");
				String end = node.substring(last + 1, node.length());
				String newEnd = "></" + end + ">";
				node = node + newEnd;
			}
			buffer.append(node);
		}
		// logger.info("bulidxml的格式："+buffer.toString());
		return buffer.toString();
	}

	public static JSONObject getJSONObjFromXml(String response)
			throws DocumentException {
		return elementToJSONObject(strToDocument(response).getRootElement());
	}

	public static JSONObject elementToJSONObject(Element node) {
		JSONObject result = new JSONObject();
		// 当前节点的名称、文本内容和属性
		List<Attribute> listAttr = node.attributes();// 当前节点的所有属性的list
		for (Attribute attr : listAttr) {// 遍历当前节点的所有属性
			result.put(attr.getName(), attr.getValue());
		}
		// 递归遍历当前节点所有的子节点
		List<Element> listElement = node.elements();// 所有一级子节点的list
		if (!listElement.isEmpty()) {
			for (Element e : listElement) {// 遍历所有一级子节点
				if (e.attributes().isEmpty() && e.elements().isEmpty()) // 判断一级节点是否有属性和子节点
					result.put(e.getName(), e.getTextTrim());// 沒有则将当前节点作为上级节点的属性对待
				else {
					if (!result.containsKey(e.getName())) // 判断父节点是否存在该一级节点名称的属性
						result.put(e.getName(), new JSONArray());// 没有则创建
					((JSONArray) result.get(e.getName()))
							.add(elementToJSONObject(e));// 将该一级节点放入该节点名称的属性对应的值中
				}
			}
		}
		return result;
	}

	public static Document strToDocument(String xml) throws DocumentException {
		return DocumentHelper.parseText(xml);
	}

}
