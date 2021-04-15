package com.rj.bd.user.service;

import java.util.List;
import java.util.Map;

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


	public void save(User user) {

		userMapper.save(user);
	}


	public void delete(String uId) {

		userMapper.delete(uId);
	}


	public Map<String, Object> findById(String uId) {
		return userMapper.findById(uId);
	}


	public void update(User user) {
		userMapper.update(user);
	}


	public void deleterecord(String uId) {
		userMapper.deleterecord(uId);
	}


	public String queryImgById(String uId) {
		return userMapper.queryImgById(uId);
	}

	



}
