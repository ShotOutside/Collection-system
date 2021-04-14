package com.rj.bd.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.rj.bd.user.entity.User;

@Repository("userMapper")

public interface UserMapper {
	
	
	
	//查询
	@Select("select * from user")
	public List<User> findAll();
	
	
	//条件查询(id)
	@Select("SELECT * FROM USER WHERE uId=#{uId}")
	public Map<String, Object> findById(String uId);
	
	//添加
	@Insert("insert into user (uId,uName,uTel,uSex) values(#{uId},#{uName},#{uTel},#{uSex})")
	public void save(User user);
	
	
	//删除
	@Delete("delete from user where uId=#{uId}")
	public void delete(String uId);
	
	//修改
	@Update("update user set uName=#{uName},uTel=#{uTel},uSex=#{uSex} where uId=#{uId}")
	public void update(User user);
	
}
