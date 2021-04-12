package com.rj.bd.log.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.bd.log.dao.LogMapper;
import com.rj.bd.log.entity.Log;



@Service("logService")
public class LogServiceImpl implements ILogService {
	
	@Autowired
	public LogMapper logMapper;

	public List<Log> findAll() {
		return logMapper.findAll();
	}



}
