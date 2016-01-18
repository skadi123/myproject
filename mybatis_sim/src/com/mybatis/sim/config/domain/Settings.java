package com.mybatis.sim.config.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

public class Settings {

	private List<Setting> settingList = new ArrayList<Setting>();
	
	private Map<String, String> settingMapping = new HashMap<String, String>();

	@XmlElement(name = "setting")
	public List<Setting> getSettingList() {
		return settingList;
	}

	public void setSettingList(List<Setting> settingList) {
		this.settingList = settingList;
	}
	
	//将setting集合模式转化为Map集合模式，方便使用key取值
	public void initMapping() {
		for (Setting s : settingList) {
			settingMapping.put(s.getName(), s.getValue());
		}
	}
	
	public String getSettingValue(String settingName) {
		return settingMapping.get(settingName);
	}
	
}
