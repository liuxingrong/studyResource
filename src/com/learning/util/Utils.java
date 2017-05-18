package com.learning.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;

public class Utils {
	
	public static String ObjToJson(Object object) {
		// 使java.sql.Date转换为json对象
		JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonBeanProcessor(java.sql.Date.class, new JsDateJsonBeanProcessor());
		return JSONObject.fromObject(object, jsonConfig).toString();
	}
	
	public static String ListToJson(List<Object> list) {
		JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonBeanProcessor(java.sql.Date.class, new JsDateJsonBeanProcessor());
		return JSONArray.fromObject(list, jsonConfig).toString();
	}
}
