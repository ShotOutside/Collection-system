package com.rj.bd.root.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.base.BaseController;
import com.rj.bd.root.dao.RootMapper;
import com.rj.bd.root.entity.Root;
import com.rj.bd.root.service.IRootService;


@Controller
@RequestMapping("/root")
public class RootController{

	@Autowired
	public IRootService rootService;

	
	
	
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
		
	
}



