package com.rj.bd.record.entity;

import java.util.Date;

import com.rj.bd.user.entity.User;
import com.rj.bd.way.entity.Way;

import lombok.Data;

@Data
public class Record {

	
	public String  cr_id;
	public Integer cr_money;
	public Date cr_time;
	public String cr_remark;
	

	public String uId;
	public String w_id;
	
	
	
	
	
	
}
