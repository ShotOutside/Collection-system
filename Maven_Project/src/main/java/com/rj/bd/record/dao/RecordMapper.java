package com.rj.bd.record.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.rj.bd.record.entity.Record;
import com.rj.bd.user.entity.User;

@Repository("recordMapper")
public interface RecordMapper {

	
		//查询全部
		@Select("SELECT * FROM record r,USER u,way w WHERE r.uId=u.uId AND r.w_id=w.w_id" )
		public List<Map<String, Object>> find();
		
		//条件查询
		@Select("SELECT * FROM record,user,way WHERE user.uId=record.uId AND record.w_id=way.w_id AND user.uName=#{uName}" ) 
		public List<Map<String, Object>> findAll(User user);
	
		//添加
		@Insert ("insert into record (cr_id,cr_money,u_id,w_id,cr_time,cr_remark) values (#{cr_id},#{cr_money},#{u_id},#{w_id},#{cr_time},#{cr_remark}")
		public void save(Record record);

		
		//修改
		@Update("update record set cr_money=#{cr_money},u_id=#{u_id},w_id=#{w_id},cr_time=#{cr_time},cr_remark=#{cr_remark} where cr_id=#{cr_id}")
		public Record update(String cr_id);
		
		//删除
		@Delete("delete from record where cr_id=#{cr_id}")
		public void delete(String cr_id);
		
		//查询一条user
		@Select("select * from user where uName=#{uName}")
		public List<User> queryByuName(String uName);
}
