package com.rj.bd.way.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.bd.way.dao.WayMapper;
import com.rj.bd.way.entity.Way;

@Service("wayService")
public class WayServiceImpl implements IWayService {

	
	
	@Autowired
	public WayMapper wayMapper;

	public List<Way> findAll() {
		return wayMapper.findAll();
	}
}
