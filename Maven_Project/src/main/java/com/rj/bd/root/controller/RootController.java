package com.rj.bd.root.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.base.BaseController;
import com.rj.bd.root.dao.RootMapper;
import com.rj.bd.root.entity.Root;
import com.rj.bd.root.service.IRootService;
import com.rj.bd.user.entity.User;


@Controller
@RequestMapping("/root")
public class RootController{

	@Autowired
	public IRootService rootService;
	private Map<String, Object> data;
	private File upload;
	private String r_name;
	private String r_password;
	private String r_username;
	private String r_sex;
	private String r_age;
	private String r_remark;
	
	

	
	
	
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(@RequestParam("r_username") String r_username,@RequestParam("r_password") String r_password){
		System.out.println("login");
		System.out.println(r_username);
		System.out.println(r_password);
		Map<String , Object> map = new HashMap<String, Object>();
		Root tRoot = new Root();
		tRoot =rootService.findRootByname(r_username);
		
		if (tRoot==null) {
			map.put("code","-1");
			map.put("msg", "用户名不存在");
			return map;
		} else {
				
			String password1 =tRoot.getR_password();
			if(password1.equals(r_password)){
				map.put("code","200");
				map.put("msg","登录成功");
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("token" ,tRoot.getToken());
				map.put("data", data);
				return map;
			}else {
				map.put("code","-1");
				map.put("msg","密码错误");
				return map;
			}
		}
		
		
	}
	
	
	@RequestMapping("/query")
	@ResponseBody
	public List<Root> query(HttpServletRequest request ,HttpServletResponse response){
		
		System.out.println("query");
		List<Root> list = rootService.findAll();
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		return list;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String save(){
		Map<String, Object> data = new HashMap<String, Object>();
		if(r_username==null || r_password==null){
			this.data =print(data, "-1","请输入账号密码");
			return null;
		}
		if (upload==null) {
			this.data =print(data, "-1", "失败");
			return null;
		}
								//ServletActionContext.getServletContext();
		
		return null;
		
		
	}
	


	private Map<String, Object> print(Map<String, Object> data, String code, String msg) {
		data.put("code", code);
		data.put("msg", msg);
		
		return data;
	}
	
		
	
}



