package com.rj.bd.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.bd.user.dao.UserMapper;
import com.rj.bd.user.entity.User;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	public UserMapper userMapper;

	
	public List<User> findAll() {
		
		return userMapper.findAll();
	}

	



}
