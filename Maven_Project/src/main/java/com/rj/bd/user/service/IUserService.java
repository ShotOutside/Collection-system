package com.rj.bd.user.service;

import java.util.List;
import java.util.Map;

import com.rj.bd.user.entity.User;



public interface IUserService {

	List<User> findAll();
	
	Map<String, Object> findById(String uId);

	void save(User user);

	void delete(String uId);
	
	void update(User user);

}
