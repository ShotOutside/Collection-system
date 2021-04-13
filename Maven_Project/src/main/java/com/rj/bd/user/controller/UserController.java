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
public class UserController extends BaseController {

	@Autowired
	public IUserService userService;
	private Map<String, Object> data;
	private String u_id;
	private String u_name;
	private String u_tel;
	private String u_sex;
	
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
		user.getU_id();
		user.getU_name();
		user.getU_Sex();
		user.getU_tel();
		
		userService.save(user);
		
		Map<String, Object> data1 = new HashMap<String, Object>();
		data1.put("src", "");
		data.put("data", data1);
		this.data = print(data, "0", "success");
		return data1;
		
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


	public void setU_id(String u_id) {
		this.u_id = u_id;
	}


	public void setU_name(String u_name) {
		this.u_name = u_name;
	}


	public void setU_tel(String u_tel) {
		this.u_tel = u_tel;
	}


	public void setU_sex(String u_sex) {
		this.u_sex = u_sex;
	}
	
	
	
}
