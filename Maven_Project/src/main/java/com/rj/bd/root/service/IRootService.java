package com.rj.bd.root.service;

import java.util.List;

import com.rj.bd.root.entity.Root;
import com.rj.bd.user.entity.User;

public interface IRootService {

	Root findRootByname(String r_username);

	List<Root> findAll();

	void saveImg(Root root);

	String queryByIdAvatar(String r_username);



}
