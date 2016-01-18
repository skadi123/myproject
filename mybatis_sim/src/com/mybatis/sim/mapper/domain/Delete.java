package com.mybatis.sim.mapper.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Delete {
	
	private String id;
	
	private String parameterType;
	
	private String sql;
	
	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute
	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}
	
	@XmlValue
	public String getSql() {
		return sql.trim();
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
}
