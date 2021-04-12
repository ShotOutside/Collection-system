package com.rj.bd.record.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.rj.bd.record.entity.Record;

@Repository("recordMapper")
public interface RecordMapper {

	
		//查询
		@Select("select * from record" ) 
		public List<Record> findAll();
	
		//添加
		@Insert ("insert into record (cr_id,cr_money,u_id,w_id,cr_time,cr_remark) values (#{cr_id},#{cr_money},#{u_id},#{w_id},#{cr_time},#{cr_remark}")
		public void save(Record record);

		
		//修改
		@Update("update record set cr_money=#{cr_money},u_id=#{u_id},w_id=#{w_id},cr_time=#{cr_time},cr_remark=#{cr_remark} where cr_id=#{cr_id}")
		public Record update(String cr_id);
		
		//删除
		@Delete("delete from record where cr_id=#{cr_id}")
		public Record delete(String cr_id);
}
