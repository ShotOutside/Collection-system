package com.rj.bd.rankglade.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rj.bd.base.BaseController;
import com.rj.bd.member.entity.Member;
import com.rj.bd.member.service.IMemberService;
import com.rj.bd.rankglade.entity.Rank;
import com.rj.bd.rankglade.service.IRankService;


@Controller
@RequestMapping("/rank")
public class RankController extends BaseController{
	
	@Autowired
	public IRankService rankService;
	
	
	
	
	
	
	@RequestMapping("/query")
	@ResponseBody
	public List<Rank> query(HttpServletRequest request,HttpServletResponse response){
		
		System.out.println("query");
		List<Rank> list = rankService.findAll();
		
		
		return list;
		

		
	}
	
	

}
