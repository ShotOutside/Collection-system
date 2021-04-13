package com.rj.bd.root.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.rj.bd.root.entity.Root;

@Repository("rootMapper")

public interface RootMapper {
	
	
	@Select("select * from root where r_username=#{r_username}")
	public Root findRootByname(String r_username);

	
	
	
	
}