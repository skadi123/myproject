package com.leehat.dao;

import java.util.List;

import com.leehat.pojo.User;

public interface BaseDao {
	List<User> queryUserInfo();
}
