package com.rj.bd.user.controller;

import java.util.List;

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
	
	@RequestMapping("/query")
	@ResponseBody
	public List<User> query(HttpServletRequest request ,HttpServletResponse response){
		System.out.println("query");
		List<User> list = userService.findAll();
		for (User user : list) {
			System.out.println(list);
		}
		
		
		return list;
		
	}
	
	
}
