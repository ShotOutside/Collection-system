package com.rj.bd.record.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.rj.bd.record.dao.RecordMapper;
import com.rj.bd.record.entity.Record;
import com.rj.bd.user.entity.User;

@Service("recordService")
public class RecordImpl implements IRecordService {
	
	@Autowired
	public RecordMapper recordMapper;

	public List<Map<String, Object>> findAll(User user,Integer page,Integer size) {
		PageHelper.startPage(page, size);
		return recordMapper.findAll(user);
	}

	public List<Map<String, Object>> find() {
		return recordMapper.find();
	}

	public String queryByuName(String uName) {

		return recordMapper.queryByuName(uName);
		
	}

	public void delete(String cr_id) {

		recordMapper.delete(cr_id);
	}

	public void save(Record record) {
		recordMapper.save(record);
		
	}

	public List<Map<String, Object>> queryEcharts() {
		return recordMapper.queryEcharts();
	}

	public List<Map<String,Object>> queryAll(Integer page, Integer size) {
		PageHelper.startPage(page, size);
		
		return recordMapper.queryAll(page, size);
	}





	
	
	
	 
	

}
