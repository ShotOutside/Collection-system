package com.rj.bd.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.bd.member.dao.MemberMapper;
import com.rj.bd.member.entity.Member;

@Service("memberService")
public class MemberServiceImpl implements IMemberService {
	
	@Autowired
	public MemberMapper memberMapper;

	public List<Map<String, Object>> findAll() {
		return memberMapper.findAll();
	}

	public void save(Member member) {
		memberMapper.save(member);
	}

	public List<Map<String, Object>> findByName(Member member ) {
		return memberMapper.findByName(member);
	}



}
