package com.rj.bd.way.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.rj.bd.way.entity.Way;

@Repository("wayMapper")
public interface WayMapper {

	@Select("select * from way ")
	public List<Way> findAll();
	
	
	
	
	
}
