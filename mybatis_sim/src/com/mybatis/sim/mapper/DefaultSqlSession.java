package com.mybatis.sim.mapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.mybatis.sim.executor.ExecutorFactory;
import com.mybatis.sim.mapper.domain.ResultMap;
import com.mybatis.sim.xmlBuilder.XmlConfigBuilder;

public class DefaultSqlSession implements SqlSession {
	
	XmlConfigBuilder xmlConfigBuilder = XmlConfigBuilder.getInstance();
	/**
	 * ①通过namespace可以锁定到用户配置文件中的具体的SQL 
	 * ②利用object重新组装SQL 
	 * ③调用executor执行SQL
	 * */
	
	@Override
	public void insert(String namespace, Object object) {
		//根据namespace获取mapper.xml里的sql语句
		String nativeSql = xmlConfigBuilder.getConfigSql(namespace);
		// 获取sql语句需要填写的位置，结果:#{username}, #{userage}, #{userdes}
		String str = nativeSql.substring(nativeSql.indexOf("#") ,
				nativeSql.length() - 1);
		// 做切割出入到数组
		String[] strs = str.split(",");
		String[] values = new String[strs.length];

		for (int i = 0; i < strs.length; i++) {
			// #{username}--->username
			String s = strs[i].substring(strs[i].indexOf("{") + 1,
					strs[i].length() - 1);
			// 由于有特殊符号，要取消特殊含义并拼接，结果:\\#\\{name\\}
			strs[i] = "\\#\\{" + s + "\\}";
			// 把#{username}--->对应Bean中该字段的值
			values[i] = "'" + this.getValue(s, object) + "'";
			// 拿到结果做替换
			nativeSql = nativeSql.replaceAll(strs[i], values[i]);
		}
		//获取configuration.xml的setting里对应的执行模式
		String _type = xmlConfigBuilder.getSettingsProperty("defaultExecutorType");
		int type = 0;
		if (_type.equals("SIMPLE")) {
			type = 1;
		}
		//执行连接JDBC
		ExecutorFactory.getInstance().getExecutor(type).execute(nativeSql);
	}
	
	//select语句的拼接
	@Override
	public List<Object> select(String namespace) {
		String nativeSql = (String) xmlConfigBuilder.getSelectSql(namespace).get("SelectSql");
		//System.out.println(nativeSql);
		//获取configuration.xml的setting里对应的执行模式
		String _type = xmlConfigBuilder.getSettingsProperty("defaultExecutorType");
		int type = 0;
		if (_type.equals("SIMPLE")) {
			type = 1;
		}
		
		return ExecutorFactory.getInstance().getExecutor(type).executeQuery(
				nativeSql, (ResultMap) xmlConfigBuilder.getSelectSql(namespace).get("SelectSqlresultMapObject"));
	}
	
	/**
	 * 通过ID查询
	 */
	public List<Object> selectOne (String namespace ,String id) {
		String nativeSql = (String) xmlConfigBuilder.getSelectSql(namespace).get("SelectByIdSql");
		String _value = "\\#\\{" + nativeSql.substring(nativeSql.indexOf("#") + 2, nativeSql.length() - 1) + "\\}";
		nativeSql = nativeSql.replaceAll(_value, "'" + id + "'");
		String _type = xmlConfigBuilder.getSettingsProperty("defaultExecutorType");
		int type = 0;
		if (_type.equals("SIMPLE")) {
			type = 1;
		}
		return ExecutorFactory.getInstance().getExecutor(type).executeQuery(
				nativeSql, (ResultMap) xmlConfigBuilder.getSelectSql(namespace).get("SelectByIdSqlresultMapObject"));
	}
	
	@Override
	public void delete(String namespace, String id) {
		String nativeSql = xmlConfigBuilder.getDelSql(namespace);
		String _value = "\\#\\{" + nativeSql.substring(nativeSql.indexOf("#") + 2, nativeSql.length() - 1) + "\\}";
		nativeSql = nativeSql.replaceAll(_value, "'" + id + "'");
		String _type = xmlConfigBuilder.getSettingsProperty("defaultExecutorType");
		int type = 0;
		if (_type.equals("SIMPLE")) {
			type = 1;
		}
		ExecutorFactory.getInstance().getExecutor(type).execute(nativeSql);
	}
	
	public void update(String namespace, Object object) {
		//从xml获取sql语句并转化为小写
		String nativeSql = xmlConfigBuilder.getUpdate(namespace).toLowerCase();
		//获取where语句后面的条件带有#{},如:#{name}
		String condition = nativeSql.substring(nativeSql.lastIndexOf("#"));
		//将#{}去除,如:name
		String _condition = condition.substring(condition.indexOf("#") + 2, condition.length() - 1);
		//获取sql语句中需要改变值字段,如:gender = #{gender}, age = #{age},并拼接前面的条件,如gender = #{gender}, age = #{age}, name = #{name}
		String _nativeSql = nativeSql.substring(nativeSql.indexOf("set") + 4,nativeSql.indexOf("where") - 1) + ", " 
				+ _condition + " = " + condition;
		String[] strs = _nativeSql.split(",");
		String[] values = new String[strs.length];
		for (int i = 0 ; i < strs.length ; i++) {
			//去除#{}
			String value = strs[i].substring(strs[i].indexOf("{") + 1, strs[i].lastIndexOf("}"));
			//取消特殊符号意思
			String _value = "\\#\\{" + value + "\\}";
			values[i] = "'" + this.getValue(value, object) + "'";
			nativeSql = nativeSql.replaceAll(_value, values[i]);
 		}
		String _type = xmlConfigBuilder.getSettingsProperty("defaultExecutorType");
		int type = 0;
		if (_type.equals("SIMPLE")) {
			type = 1;
		}
		ExecutorFactory.getInstance().getExecutor(type).execute(nativeSql);
	}
	
	private String getValue(String key, Object object) {
		String methodName = "get" + Character.toUpperCase(key.charAt(0))
				+ key.substring(1);
		Method[] methods = object.getClass().getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				try { 
					return (String) method.invoke(object);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
