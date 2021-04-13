package com.rj.bd.member.service;

import java.util.List;
import java.util.Map;

import com.rj.bd.member.entity.Member;

public interface IMemberService {

	List<Map<String, Object>> findAll();
	
	public void save(Member member);

}
