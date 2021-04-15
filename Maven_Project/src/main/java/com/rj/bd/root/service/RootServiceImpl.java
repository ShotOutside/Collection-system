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

	public Root findRootByname(String r_username) {
		
		return rootMapper.findRootByname(r_username);
	}

	public List<Root> findAll() {
		return rootMapper.findAll();
	}

	public void saveImg(Root root) {

		rootMapper.saveImg(root);
	}



}
