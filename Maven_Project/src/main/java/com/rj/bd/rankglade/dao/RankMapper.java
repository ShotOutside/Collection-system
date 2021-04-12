package com.rj.bd.rankglade.dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.rj.bd.rankglade.entity.Rank;



@Repository("rankMapper")
public interface RankMapper {
	
	
	//查询
	@Select ("select * from rankglade ")
	public List<Rank> findAll();
	
	
	
	//添加
	@Insert ("insert into rankglade (g_id,g_name,g_remark) values (#{g_id},#{g_name},#{g_remark}")
	public void save(Rank rank);

	
	//修改
	@Update("update rankglade set g_name=#{g_name},g_remark=#{g_remark} where g_id=#{g_id}")
	public Rank update(String g_id);
	
	//删除
	@Delete("delete from rankglade where g_id=#{g_id}")
	public Rank delete(String g_id);
}
