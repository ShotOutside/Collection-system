package com.rj.bd.root.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.bd.root.dao.RootMapper;
import com.rj.bd.root.entity.Root;
import com.rj.bd.user.entity.User;

@Service("rootService")
public class RootServiceImpl implements IRootService {
	
	@Autowired
	public RootMapper rootMapper;

	
	
	//根据姓名查询
	public Root findRootByname(String r_username) {
		
		return rootMapper.findRootByname(r_username);
	}

	//查询全部
	public List<Root> findAll() {
		return rootMapper.findAll();
	}

	//添加
	public void saveImg(Root root) {

		rootMapper.saveImg(root);
	}
	//根据r_username查询r_img
	public String queryByIdAvatar(String r_username) {
		return rootMapper.queryByIdAvatar(r_username);
	}



}
