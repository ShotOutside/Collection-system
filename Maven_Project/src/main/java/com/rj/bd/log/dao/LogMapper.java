package com.rj.bd.log.dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.rj.bd.log.entity.Log;



@Repository("logMapper")
public interface LogMapper {
	
	
	//查询
	@Select ("select * from log ")
	public List<Log> findAll();
	
	
	
	//添加
	@Insert ("insert into log (l_id,l_time,l_remark) values (#{l_id},#{l_time},#{l_remark}")
	public void save(Log log);

	
	//修改
	@Update("update log set l_time=#{l_time},l_remark=#{l_remark} where l_id=#{l_id}")
	public Log update(String l_id);
	
	//删除
	@Delete("delete from log where l_id=#{l_id}")
	public Log delete(String l_id);
}
