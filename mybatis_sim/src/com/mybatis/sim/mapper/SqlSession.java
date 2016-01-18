package com.mybatis.sim.mapper;

import java.util.List;

public interface SqlSession {

	void insert(String namespace, Object object);
	
	List<Object> select(String namespace);
	
	List<Object> selectOne (String namespace, String id);
	
	void delete(String namespace, String id);
	
	void update(String namespace, Object object);
}
