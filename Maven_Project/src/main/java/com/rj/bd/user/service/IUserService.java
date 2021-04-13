package com.rj.bd.user.service;

import java.util.List;

import com.rj.bd.user.entity.User;



public interface IUserService {

	List<User> findAll();

	void save(User user);

	void delete(String uId);

}
