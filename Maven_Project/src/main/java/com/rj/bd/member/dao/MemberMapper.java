package com.rj.bd.member.dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.rj.bd.member.entity.Member;

@Repository("memberMapper")
public interface MemberMapper {
	
	
	//查询
	@Select ("select * from member ")
	public List<Member> findAll();
	
	
	
	//添加
	@Insert ("insert into member (m_id,m_name,m_sex,m_tel,g_id) values (#{m_id},#{m_name},#{m_sex},#{m_tel},#{g_id})")
	public void save(Member com);

	
	//修改
	@Update("update member set m_name=#{m_name},m_sex=#{m_sex},m_tel=#{m_tel},g_id=#{g_id} where m_id=#{m_id}")
	public Member update(String m_id);
	
	//删除
	@Delete("delete from member where m_id=#{m_id}")
	public Member delete(String m_id);
}
