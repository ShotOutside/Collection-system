package com.rj.bd.record.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.rj.bd.way.entity.Way;

@Controller
@RequestMapping("/record")
public class RecordController extends BaseController{
	
	@Autowired
	public IRecordService recordService;
	private Map<String, Object> data;
	private String  cr_id;
	private String uName;
	private Integer cr_money;
	private String w_name;
	private Date cr_time; 
	
	
	
	
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
	
	@RequestMapping("/add")
	@ResponseBody
	public Map<String , Object> save(HttpServletRequest request ,HttpServletResponse response) throws ParseException{
		//SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		Record record = new Record();
		/*record.setCr_id(request.getParameter("Cr_id"));
		record.setCr_money(Integer.getInteger(request.getParameter("cr_money")));
		record.setCr_time(sdf.parse(request.getParameter("cr_time")));*/
		//record.setCr_id("0111");
		//record.setCr_money(45);
		User user = new User();
		//user.setUName(request.getParameter("uName"));
		user.setUName("士大夫");
		List<User> list =recordService.queryByuName(user.getUName());
		System.out.println(list);
		
		/*Way way = new Way();
		way.setW_name(request.getParameter("w_name"));
		record.setUser(user);
		record.setWay(way);
		System.out.println(record);*/
		return null;
		
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(String cr_id){
		Map<String , Object> data = new HashMap<String, Object>();
		System.out.println("delete");
		System.out.println("cr_id" +":" + cr_id);
		recordService.delete(cr_id);
		this.data = print(data, "0", "success");
		
		
		return null;
	}
	
	
	
	
	
	


	private Map<String, Object> print(Map<String, Object> data, String code, String msg) {
		data.put("code", code);
		data.put("msg", msg);
		
		return data;
	}


	public void setCr_id(String cr_id) {
		this.cr_id = cr_id;
	}


	public void setuName(String uName) {
		this.uName = uName;
	}


	public void setCr_money(Integer cr_money) {
		this.cr_money = cr_money;
	}


	public void setW_name(String w_name) {
		this.w_name = w_name;
	}


	public void setCr_time(Date cr_time) {
		this.cr_time = cr_time;
	}
	

	
	
	
	
	
	
}
