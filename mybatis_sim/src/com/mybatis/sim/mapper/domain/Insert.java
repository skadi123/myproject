package com.mybatis.sim.mapper.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Insert {
	
	private String id;
	
	private String parameterType;
	
	private String useGeneratedKeys;
	
	private String keyProperty;
	
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

	@XmlAttribute
	public String getUseGeneratedKeys() {
		return useGeneratedKeys;
	}

	public void setUseGeneratedKeys(String useGeneratedKeys) {
		this.useGeneratedKeys = useGeneratedKeys;
	}

	@XmlAttribute
	public String getKeyProperty() {
		return keyProperty;
	}
	
	public void setKeyProperty(String keyProperty) {
		this.keyProperty = keyProperty;
	}

	@XmlValue
	public String getSql() {
		return sql.trim();
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
}
