package com.mybatis.sim.executor;

import java.util.List;

import com.mybatis.sim.mapper.domain.ResultMap;

public interface IExecutor {

	void execute(String sql);
	
	List<Object> executeQuery(String sql, ResultMap resultMap);
}
