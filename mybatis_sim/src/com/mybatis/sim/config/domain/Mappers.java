package com.mybatis.sim.config.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Mappers {
	
	private List<MapperConfig> mapperList = new ArrayList<MapperConfig>();
	
	@XmlElement(name = "mapper")
	public List<MapperConfig> getMapperList() {
		return mapperList;
	}

	public void setMapperList(List<MapperConfig> mapperList) {
		this.mapperList = mapperList;
	}
	
}
