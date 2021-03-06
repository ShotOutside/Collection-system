package com.rj.bd.record.service;

import java.util.List;
import java.util.Map;

import com.rj.bd.record.entity.Record;
import com.rj.bd.user.entity.User;

public interface IRecordService {

	List<Map<String, Object>> findAll(User user, Integer page, Integer size);

	List<Map<String, Object>> find();

	String queryByuName(String uName);

	void delete(String cr_id);

	void save(Record record);

	List<Map<String, Object>> queryEcharts();

	List<Map<String,Object>> queryAll(Integer page, Integer size);



}
