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
	
	@RequestMapping("/query")
	@ResponseBody
	public List<User> query(HttpServletRequest request ,HttpServletResponse response){
		System.out.println("query");
		List<User> list = userService.findAll();
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		return list;
		
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
	
	
}
