package com.mybatis.sim.test;

import java.util.List;

import com.mybatis.sim.business.entity.User;
import com.mybatis.sim.mapper.DefaultSqlSession;
import com.mybatis.sim.mapper.SqlSession;

public class Test {
	/**
	 * ①两个配置文件的加载:XmlConfigBuilder
	 * ②模拟框架使用的方法：getSqlSession.insert()插入数据
	 * */
	public static void main(String[] args) {
		User user = new User();
//		user.setAge("22");
//		user.setGender("m");
//		user.setName("lisi");
		SqlSession sqlSession = new DefaultSqlSession();
//		sqlSession.insert("com.mybatis.sim.business.entity.User.saveUser", user);
		
//		user.setAge("100");
//		user.setGender("x");
//		user.setName("lisi");
//		sqlSession.update("com.mybatis.sim.business.entity.User.update", user);
//		List<Object> listObList = sqlSession.selectOne("com.mybatis.sim.business.entity.User.selectByid", "lisi");
//		for (Object object : listObList) {
//			System.out.println(object.toString());
//		}
//		listObList = sqlSession.selectOne("com.mybatis.sim.business.entity.User.selectByid", "lisi");
		//参数:namespace，数据库表操作对应的实体类
//		List<Object> listObList = sqlSession.select("com.mybatis.sim.business.entity.User.queryMessageList");
//		for (Object object : listObList) {
//			System.out.println(object.toString());
//		}
		sqlSession.delete("com.mybatis.sim.business.entity.User.delete", "lisi");
	}
}
