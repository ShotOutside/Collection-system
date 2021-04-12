package com.rj.bd.rankglade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.rj.bd.rankglade.dao.RankMapper;
import com.rj.bd.rankglade.entity.Rank;

@Service("rankService")
public class RankServiceImpl implements IRankService {
	
	@Autowired
	public RankMapper rankMapper;

	public List<Rank> findAll() {
		return rankMapper.findAll();
	}



}
