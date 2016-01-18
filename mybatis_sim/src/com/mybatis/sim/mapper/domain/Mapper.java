package com.mybatis.sim.mapper.domain;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mybatis.sim.mapper.domain.ResultMap;
import com.mybatis.sim.mapper.domain.Insert;

@XmlRootElement(name = "mapper")
public class Mapper {
	
	private List<ResultMap> resultMap;
	
	private List<Insert> insert;
	
	private String namespace;
	
	private List<Select> select;
	
	private List<Delete> delete;
	
	private List<Update> update;
	
	@XmlElement(name = "update")
	public List<Update> getUpdate() {
		return update;
	}

	public void setUpdate(List<Update> update) {
		this.update = update;
	}
	
	@XmlElement(name = "resultMap")
	public List<ResultMap> getResultMap() {
		return resultMap;
	}

	public void setResultMap(List<ResultMap> resultMap) {
		this.resultMap = resultMap;
	}
	
	@XmlElement(name = "delete")
	public List<Delete> getDelete() {
		return delete;
	}

	public void setDelete(List<Delete> delete) {
		this.delete = delete;
	}

	@XmlElement(name = "insert")
	public List<Insert> getInsert() {
		return insert;
	}

	public void setInsert(List<Insert> insert) {
		this.insert = insert;
	}
	
	@XmlElement(name = "select")
	public List<Select> getSelect() {
		return select;
	}

	public void setSelect(List<Select> select) {
		this.select = select;
	}
	
	@XmlAttribute
	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	public void init() {
		for (ResultMap resultMap : this.resultMap) {
			resultMap.init();
		}
	}
	
}
