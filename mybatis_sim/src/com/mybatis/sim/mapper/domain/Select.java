package com.mybatis.sim.mapper.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * 
 * @author 219387
 *
 */
public class Select {
	
	private String id;
	
	private String resultMap;
	
	private String sql;
	
	private String resultType; 
	
	@XmlAttribute
	public String getResultType() {
		return resultType;
	}
	
	
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@XmlAttribute
	public String getResultMap() {
		return resultMap;
	}

	public void setResultMap(String resultMap) {
		this.resultMap = resultMap;
	}
	
	@XmlValue
	public String getSql() {
		return sql.trim();
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	
}	
