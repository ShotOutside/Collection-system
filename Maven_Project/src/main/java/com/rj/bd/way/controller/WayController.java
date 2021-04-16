package com.rj.bd.way.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


import com.rj.bd.way.entity.Way;
import com.rj.bd.way.service.IWayService;


@Controller
@RequestMapping("/way")
public class WayController {

	@Autowired
	public IWayService wayService;
	
	@RequestMapping("/query11")
	public String queryFK(HttpServletRequest request,ModelMap map){
		System.out.println("UserController:queryFK()");
		
	     List<Way> list = wayService.findAll();
		
		System.out.println("--------------->"+list.size());
		
		//request.setAttribute("list", list);
		map.addAttribute("list", list);
		String path = request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath() + "/";
		map.addAttribute("path", path);
		for (Way way : list) {
			System.out.println(way.getW_id()+"\t"+way.getW_name());
		}
		
		return "list_fk";
	}
}
