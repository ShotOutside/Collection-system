package com.rj.bd.root.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Repository;

import com.rj.bd.root.entity.Root;

@Repository("rootMapper")

public interface RootMapper {
	
	
	@Select("select * from root where r_username=#{r_username}")
	public Root findRootByname(String r_username);

	
	@Select("select * from root ")
	public List<Root> findAll();
	
	
	
	
	@Insert("insert into root (r_id,r_sex,r_age,r_name,r_username,r_remark,r_img,r_password) values (#{r_id},#{r_sex},#{r_age},#{r_name},#{r_username},#{r_remark},#{r_img},#{r_password})")
	public void saveImg(Root root);
	
	
	@Select("select r_img from root where r_username=#{r_username}")
	public String queryByIdAvatar(String r_username);
}
