package com.mybatis.sim.mapper.domain;

import javax.xml.bind.annotation.XmlAttribute;

public class Id {
	
	private String property;
	
	private String colum;
	
	@XmlAttribute
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	
	@XmlAttribute
	public String getColum() {
		return colum;
	}

	public void setColum(String colum) {
		this.colum = colum;
	}
	
}
