package com.rj.bd.user.controller;

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
import com.rj.bd.user.entity.User;
import com.rj.bd.user.service.IUserService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	public IUserService userService;
	private Map<String, Object> data;
	private String uId;
	private String uName;
	private String uTel;
	private String uSex;
	
	@RequestMapping("/query")
	@ResponseBody
	public List<User> query(HttpServletRequest request ,HttpServletResponse response){
		System.out.println("query");
		List<User> list = userService.findAll();
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		return list;
		
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public Map<String , Object> save(HttpServletRequest request ,HttpServletResponse response){
		
		User user = new User();
		user.setUId(request.getParameter("uId"));
		user.setUName(request.getParameter("uName"));
		user.setUTel(request.getParameter("uTel"));
		user.setUSex(request.getParameter("uSex"));
		System.out.println(user);
		userService.save(user);
		
		Map<String, Object> data1 = new HashMap<String, Object>();
		data1.put("src", "");
		data.put("data", data1);
		this.data = print(data, "0", "success");
		return null;
		
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(String uId){
		Map<String, Object> data = new HashMap<String, Object>();
		System.out.println("delete");
		System.out.println("uId" +":"+ uId);
		userService.delete(uId);
		this.data = print(data, "0", "success");
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/queryById")
	public Map<String, Object> queryById(HttpServletRequest request ,HttpServletResponse response){
		
		System.out.println("findById");
		String uId=request.getParameter("uId");
		System.out.println("uId:"+uId);
		Map<String, Object> map=userService.findById(uId);
		System.out.println("map:"+map);
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		return map;
	}
	
	@RequestMapping("/update")
	public void update(HttpServletRequest request ,HttpServletResponse response){
		System.out.println("update");
		User user=new User();
		user.setUId(request.getParameter("uId"));
		user.setUName(request.getParameter("uName"));
		user.setUSex(request.getParameter("uSex"));
		user.setUTel(request.getParameter("uTel"));
		System.out.println("user:"+user);
		userService.update(user);
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "0");
		System.out.println("this.data"+this.data);
	}

	private Map<String, Object> print(Map<String, Object> data, String code, String msg) {
		data.put("code", code);
		data.put("msg", msg);
		
		return data;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}


	public void setuId(String uId) {
		this.uId = uId;
	}


	public void setuName(String uName) {
		this.uName = uName;
	}


	public void setuTel(String uTel) {
		this.uTel = uTel;
	}


	public void setuSex(String uSex) {
		this.uSex = uSex;
	}




	
	
}
