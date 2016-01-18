package com.mybatis.sim.mapper.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class ResultMap {
	
	private String propertyId;
	
	private String type;
	
	private Id id;
	
	List<Result> resultList = new ArrayList<Result>();
	
	Map<String, String> resultMapping = new HashMap<String, String>();
	
	@XmlAttribute
	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	
	@XmlAttribute
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@XmlElement(name = "id")
	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}
	
	@XmlElement(name = "result")
	public List<Result> getResultList() {
		return resultList;
	}

	public void setResultList(List<Result> resultList) {
		this.resultList = resultList;
	}
	
	public void init() {
		for (Result result : resultList) {
			resultMapping.put(result.getProperty(), result.getColumn());
		}
	}

	public Map<String, String> getResultMapping() {
		return resultMapping;
	}
	
}
