package com.mybatis.sim.executor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mybatis.sim.mapper.domain.ResultMap;
import com.mybatis.sim.xmlBuilder.XmlConfigBuilder;

public class SimpleExecutor implements IExecutor {
	
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement stmt = null;
	private XmlConfigBuilder xmlConfigBuilder = XmlConfigBuilder.getInstance();
	private String driver = xmlConfigBuilder.getDataSourceProperty("driver");
	private String url = xmlConfigBuilder.getDataSourceProperty("url");
	private String username = xmlConfigBuilder.getDataSourceProperty("username");
	private String password = xmlConfigBuilder.getDataSourceProperty("password");
	/**
	 * JDBC驱动，连接等操作
	 */
	public Connection execute() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection("jdbc:mysql://" + url , username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public void execute(String sql) {
			try {
				stmt = this.execute().prepareStatement(sql);
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Override
	public List<Object> executeQuery(String sql, ResultMap resultMap) {
		List<Object> reList = new ArrayList<Object>();
		try {
			stmt = this.execute().prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Object object = Class.forName(resultMap.getType()).newInstance();
				Class<?> c = object.getClass();
				for (String key : resultMap.getResultMapping().keySet()) {
					String beanProperty = key;
					String dbColumn = resultMap.getResultMapping().get(beanProperty);
					
					Field field = c.getDeclaredField(beanProperty);
					field.setAccessible(true);
					field.set(object, rs.getString(dbColumn));
				}
				reList.add(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reList;
	}
}
