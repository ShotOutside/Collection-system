package com.rj.bd.member.controller;

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
import com.rj.bd.member.entity.Member;
import com.rj.bd.member.service.IMemberService;


/**
 * member模块的控制器
 * @author LGZ
 *
 */
@Controller
@RequestMapping("/member")
public class MemberController extends BaseController{
	
	@Autowired
	public IMemberService memberService;
	
	private Map<String, Object> data;
	
	
	
	
	@RequestMapping("/query")
	@ResponseBody
	public List<Map<String, Object>> query(HttpServletRequest request,HttpServletResponse response){
		
		System.out.println("query");
		List<Map<String, Object>> list = memberService.findAll();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
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
