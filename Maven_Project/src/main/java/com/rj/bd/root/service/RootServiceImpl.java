package com.rj.bd.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.bd.root.dao.RootMapper;
import com.rj.bd.root.entity.Root;

@Service("rootService")
public class RootServiceImpl implements IRootService {
	
	@Autowired
	public RootMapper rootMapper;

	public Root findRootByname(String r_username) {
		
		return rootMapper.findRootByname(r_username);
	}

}
