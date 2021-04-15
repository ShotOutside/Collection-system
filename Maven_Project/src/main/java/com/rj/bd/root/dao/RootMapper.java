package com.rj.bd.root.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Repository;

import com.rj.bd.root.entity.Root;

@Repository("rootMapper")

public interface RootMapper {
	
	//根据姓名查询
	@Select("select * from root where r_username=#{r_username}")
	public Root findRootByname(String r_username);

	//查询全部
	@Select("select * from root ")
	public List<Root> findAll();
	
	
	
	//添加
	@Insert("insert into root (r_id,r_sex,r_age,r_name,r_username,r_remark,r_img,r_password) values (#{r_id},#{r_sex},#{r_age},#{r_name},#{r_username},#{r_remark},#{r_img},#{r_password})")
	public void saveImg(Root root);
	
	//根据r_username查询r_img
	@Select("select r_img from root where r_username=#{r_username}")
	public String queryByIdAvatar(String r_username);
}
