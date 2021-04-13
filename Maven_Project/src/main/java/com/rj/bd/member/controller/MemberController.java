package com.rj.bd.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
	
	private Member member;
	
	
	@RequestMapping("/query")
	@ResponseBody
	public List<Map<String, Object>> query(HttpServletRequest request,HttpServletResponse response){
		
		System.out.println("query");
		List<Map<String, Object>> list = memberService.findAll();
//		for (Map<String, Object> map : list) {
//			System.out.println(map);
//		}
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		return list;
	}
	
	@RequestMapping("/add")
	public void add(HttpServletRequest request,HttpServletResponse response){
		System.out.println("add");
		Member member=new Member();
		member.setM_id(UUID.randomUUID().toString());
		member.setM_name(request.getParameter("m_name"));
		member.setM_sex(request.getParameter("m_sex"));
		member.setM_tel(request.getParameter("m_tel"));
		member.setG_id(request.getParameter("g_id"));
		System.out.println(member);
		memberService.save(member);
		data = new HashMap<String, Object>();
		this.data = print(data, "0", "success");
		
	}
	
	
	@ResponseBody
	@RequestMapping("/queryByName")
	public List<Map<String, Object>> queryById(HttpServletRequest request,HttpServletResponse response){
		
		System.out.println("queryByName");
		Member member=new Member();
		member.setM_name(request.getParameter("m_name"));
		List<Map<String, Object>> list=memberService.findByName(member);
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

	public IMemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(IMemberService memberService) {
		this.memberService = memberService;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	
	
	
	

}
