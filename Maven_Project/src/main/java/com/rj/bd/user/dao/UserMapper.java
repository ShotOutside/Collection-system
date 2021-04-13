package com.rj.bd.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.rj.bd.user.entity.User;

@Repository("userMapper")

public interface UserMapper {
	
	
	
	//查询
	@Select("select * from user")

	public List<User> findAll();
	
	
	//添加
	@Insert("insert into user (u_id,u_name,u_tel,u_sex) values(#{u_id},#{u_name},#{u_tel},#{u_sex})")
	public void save(User user);
	
	
	
}
