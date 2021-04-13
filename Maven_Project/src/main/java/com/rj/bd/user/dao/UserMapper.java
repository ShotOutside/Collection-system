package com.rj.bd.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.rj.bd.user.entity.User;

@Repository("userMapper")

public interface UserMapper {
	
	
	
	//查询
	@Select("select * from user")

	public List<User> findAll();
}
