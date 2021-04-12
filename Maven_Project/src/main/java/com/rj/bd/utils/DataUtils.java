package com.rj.bd.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Desc 返回数据的封装
 * @author ZYF
 */
public class DataUtils {


	public static Map<String, Object> print(Map<String, Object> data,String code,String msg){
		data.put("code", code);
		data.put("msg", msg);
		return data;
	}
	
	public static Map<String, Object> print(Map<String, Object> data){
		data.put("code", "200");
		data.put("msg", "SUCCEED");
		return data;
	}
	
	public static Map<String, Object> print(Map<String, Object> data,String msg){
		data.put("code", "-1");
		data.put("msg", msg);
		return data;
	}
	
	public static Map<String, Object> print(String code,String msg){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("code", code);
		data.put("msg", msg);
		return data;
	}
	
	public static Long getDataTime(){
		return System.currentTimeMillis();
	}
}
