package com.rj.bd.way.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rj.bd.way.entity.Way;
import com.rj.bd.way.service.IWayService;


@Controller
@RequestMapping("/way")
public class WayController {

	@Autowired
	public IWayService wayService;
	
	@RequestMapping("/query11")
	public String queryFK(){
		System.out.println("UserController:queryFK()");
		
		wayService.findAll();
		
		
		return "add";
	}
}
