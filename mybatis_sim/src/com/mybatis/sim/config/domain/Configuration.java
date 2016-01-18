package com.mybatis.sim.config.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "configuration")
public class Configuration {

	private Settings settings;
	
	private DataSource dataSource;
	
	private Mappers mappers;

	@XmlElement(name = "settings")
	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	@XmlElement(name = "dataSource")
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@XmlElement(name = "mappers")
	public Mappers getMappers() {
		return mappers;
	}

	public void setMappers(Mappers mappers) {
		this.mappers = mappers;
	}
	
	public void initMapping() {
		this.settings.initMapping();
		this.dataSource.initMapping();
	}
	
}
