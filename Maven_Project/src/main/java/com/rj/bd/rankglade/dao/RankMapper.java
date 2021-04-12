package com.rj.bd.rankglade.dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.rj.bd.member.entity.Member;
import com.rj.bd.rankglade.entity.Rank;

@Repository("rankMapper")
public interface RankMapper {
	
	
	//查询
	@Select ("select * from member ")
	public List<Rank> findAll();
	
	
	
	//添加
	@Insert ("insert into member (m_id,m_name,m_sex,m_tel,g_id) values (#{c_id},#{c_name},#{c_money},#{c_remark})")
	public void save(Member com);

	
	//修改
	@Update("update member set c_name=#{c_name},c_money=#{c_money},c_remark=#{c_remark} where c_id=#{c_id}")
	public Member update(String m_id);
	
	//删除
	@Delete("delete from member where m_id=#{m_id}")
	public Member delete(String m_id);
}
