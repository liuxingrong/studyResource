package com.learning.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Utils {
	
	public static String ObjToJson(Object object) {
		return JSONObject.fromObject(object).toString();
	}
	
	public static String ListToJson(List<Object> list) {
		return JSONArray.fromObject(list).toString();
	}
}
