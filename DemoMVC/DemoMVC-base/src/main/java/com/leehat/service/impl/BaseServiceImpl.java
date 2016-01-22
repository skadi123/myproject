package com.leehat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.leehat.dao.BaseDao;
import com.leehat.pojo.User;
import com.leehat.service.BaseService;

@Service
public class BaseServiceImpl implements BaseService{

	@Resource
	private BaseDao baseDao;
	
	public List<User> queryUserInfo() {
		return baseDao.queryUserInfo();
	}

}
