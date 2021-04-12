package com.rj.bd.log.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rj.bd.base.BaseController;
import com.rj.bd.log.service.ILogService;


@Controller
@RequestMapping("/log")
public class LogController extends BaseController{

	@Autowired
	public ILogService logService;
	
	
	

	
	
	
}
