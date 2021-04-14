package com.rj.bd.record.service;

import java.util.List;
import java.util.Map;

import com.rj.bd.user.entity.User;

public interface IRecordService {

	List<Map<String, Object>> findAll(User user);

	List<Map<String, Object>> find();

	List<User> queryByuName(String uName);

	void delete(String cr_id);




}
