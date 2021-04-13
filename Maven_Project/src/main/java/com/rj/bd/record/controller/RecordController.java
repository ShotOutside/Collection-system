package com.rj.bd.record.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.base.BaseController;
import com.rj.bd.record.entity.Record;
import com.rj.bd.record.service.IRecordService;
import com.rj.bd.user.entity.User;

@Controller
@RequestMapping("/record")
public class RecordController extends BaseController{
	
	@Autowired
	public IRecordService recordService;
	private Map<String, Object> data;
	
	
	
	@RequestMapping("/queryByName")
	@ResponseBody
	public List<Map<String, Object>> queryByName(HttpServletRequest request,HttpServletResponse response){
		System.out.println("query");
		User user = new User();
		user.setUName(request.getParameter("uName"));
		System.out.println(user.getUName());
		List<Map<String, Object>> list = recordService.findAll(user);
		
		System.out.println(list);
		
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		return list;
	}
	
	
	@RequestMapping("/query")
	@ResponseBody
	public List<Map<String, Object>> query(HttpServletRequest request,HttpServletResponse response){
		System.out.println("query");
		List<Map<String, Object>> list = recordService.find();
		
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		return list;
		
		
		
	}
	
	


	private Map<String, Object> print(Map<String, Object> data, String code, String msg) {
		data.put("code", code);
		data.put("msg", msg);
		
		return data;
	}
	

	
	
	
}
