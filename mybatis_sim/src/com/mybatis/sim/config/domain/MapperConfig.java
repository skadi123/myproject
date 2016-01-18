package com.mybatis.sim.config.domain;

import javax.xml.bind.annotation.XmlAttribute;

public class MapperConfig {

	private String resource;

	@XmlAttribute
	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
	
}
