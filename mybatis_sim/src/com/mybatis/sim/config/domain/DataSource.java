package com.mybatis.sim.config.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

public class DataSource {
	
	private List<Property> properties = new ArrayList<Property>();
	
	private Map<String, String> propertyMapping = new HashMap<String, String>();

	@XmlElement(name = "property")
	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	
	//将property集合模式转化为Map集合模式，方便使用key取值
	public void initMapping() {
		for (Property p : properties) {
			propertyMapping.put(p.getName(), p.getValue());
		}
	}
	
	public String getPropertyValue(String propertyName) {
		return propertyMapping.get(propertyName);
	}
	
}
