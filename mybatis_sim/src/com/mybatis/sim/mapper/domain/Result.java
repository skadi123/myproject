package com.mybatis.sim.mapper.domain;

import javax.xml.bind.annotation.XmlAttribute;

public class Result {
	
	private String property;
	
	private String column;
	
	@XmlAttribute
	public String getProperty() {
		return property;
	}
	
	public void setProperty(String property) {
		this.property = property;
	}
	
	@XmlAttribute
	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}
	

	
}
