package com.mybatis.sim.xmlBuilder;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;

import com.mybatis.sim.config.domain.Configuration;
import com.mybatis.sim.config.domain.MapperConfig;
import com.mybatis.sim.mapper.domain.Delete;
import com.mybatis.sim.mapper.domain.Insert;
import com.mybatis.sim.mapper.domain.Mapper;
import com.mybatis.sim.mapper.domain.ResultMap;
import com.mybatis.sim.mapper.domain.Select;
import com.mybatis.sim.mapper.domain.Update;
import com.mybatis.sim.xmlParser.TParser;

public class XmlConfigBuilder {
	
	private static XmlConfigBuilder instance = null;
	
	private Configuration configuration;
	
	private Map<String, Mapper> mappers = new HashMap<String, Mapper>();
	
	/**
	 * 构造函数，调用加载load方法，仅且只有一次
	 */
	private XmlConfigBuilder() {
		this.loadXML();
	}
	
	/**
	 * 做一个单例模式
	 * @return
	 */
	public static XmlConfigBuilder getInstance() {
		if (instance == null) {
			instance = new XmlConfigBuilder();
		}
		return instance;
	}
	
	/**
	 * load方法
	 */
	private void loadXML() {
		TParser parser = new TParser();
		try {
			//加载configuration.xml文件,封装成Configuration对象
			configuration = parser.readConfigurationXml("configuration.xml");
			//将configuration下的属性由list转化为map
			configuration.initMapping();
			
			/*configuration.xml文件下mapper可能会有多个，
			所以放入集合中，现在做循环将可能拿到多个mapper.xml文件*/
			for (MapperConfig mapperConfig : configuration.getMappers().getMapperList()) {
				//mapperConfig.getResource()-->mapper.xml文件名，接下mapper.xml文件
				Mapper mapper = parser.readMapperXml(mapperConfig.getResource());
				mapper.init();
				//将mapper.xml(namespace)作为key，将mapper.xml整个对象作为value
				mappers.put(mapper.getNamespace(), mapper);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public String getDataSourceProperty(String propertyName) {
		return this.configuration.getDataSource().getPropertyValue(propertyName);
	}
	
	public String getSettingsProperty(String settingName) {
		return this.configuration.getSettings().getSettingValue(settingName);
	}
	
	/**
	 * 获取mapper.xml文件下的insert标签SQL语句
	 * @param namespace
	 * @return
	 */
	//namespace是mapper.xml里的namespace + insert里的id对应值
	//com.mybatis.sim.business.entity.Customer.saveCustomer
	public String getConfigSql(String namespace) {
		//saveCustomer
		String id = namespace.substring(namespace.lastIndexOf(".") + 1, namespace.length());
		//com.mybatis.sim.business.entity.Customer
		namespace = namespace.substring(0, namespace.lastIndexOf("."));
		for (Insert insert : mappers.get(namespace).getInsert()) {
			if (id.equals(insert.getId())) {
				return insert.getSql();
			} 
		}
		return "";
	}
	
	/**
	 * 获取mapper.xml文件下的delete标签SQL语句
	 * @param namespace
	 * @return
	 */
	public String getDelSql(String namespace) {
		String id = namespace.substring(namespace.lastIndexOf(".") + 1, namespace.length());
		namespace = namespace.substring(0, namespace.lastIndexOf("."));
		for (Delete delete : mappers.get(namespace).getDelete()) {
			if (id.equals(delete.getId())) {
				return delete.getSql();
			}
		} 
		return "";
	}
	
	/**
	 * 获取mapper.xml文件下的update标签SQL语句
	 * @param namespace
	 * @return
	 */
	public String getUpdate(String namespace) {
		String id = namespace.substring(namespace.lastIndexOf(".") + 1, namespace.length());
		namespace = namespace.substring(0, namespace.lastIndexOf("."));
		for (Update update : mappers.get(namespace).getUpdate()) {
			if (id.equals(update.getId())) {
				return update.getSql();
			}
		}
		return "";
	}
	/**
	 * 获取mapper.xml文件下的select标签SQL语句
	 * @param namespace
	 * @return
	 */
	public Map<String, Object> getSelectSql(String namespace) {
		//创建一Map集合用于存放SQL语句，及resultMap对象
		Map<String, Object> reMap = new HashMap<String, Object>();
		//queryMessageList
		String id = namespace.substring(namespace.lastIndexOf(".") + 1, namespace.length());
		//com.mybatis.sim.business.entity.Customer
		namespace = namespace.substring(0, namespace.lastIndexOf("."));
		for (Select select : mappers.get(namespace).getSelect()) {
			if (id.equals(select.getId())) {
			//如果读取不到resultMap，将尝试读取ResultType，否则读取ResultMap
				if (select.getResultMap() == null) {
					//将获取的sql语句存入到Map集合中
					reMap.put("SelectByIdSql", select.getSql());
					//将获取的resultMap对象存入到Map集合中
					reMap.put("SelectByIdSqlresultMapObject", 
							(ResultMap) this.getResultMapObject(namespace));	
				} else {
					reMap.put("SelectSql", select.getSql());
					reMap.put("SelectSqlresultMapObject", 
							this.getResultMapObject(namespace, select.getResultMap()));
				}
			}
		}
		return reMap;
	}
	
	/**
	 * 封装mapper.xml下的resultMap标签及以下标签到resultMap对象中
	 * selectById
	 * @param namespace
	 * @return
	 */
	public ResultMap getResultMapObject(String namespace) {
		Mapper mapper = mappers.get(namespace);
		for (ResultMap resultMap : mapper.getResultMap()) {
			return resultMap;
		}
		return null;
	}
	
	/**
	 * selectAll
	 * @param namespace
	 * @param resultMapId
	 * @return
	 */
	public ResultMap getResultMapObject(String namespace, String resultMapId) {
		Mapper mapper = mappers.get(namespace);
		for (ResultMap resultMap : mapper.getResultMap()) {
			if (resultMapId.equals(resultMap.getPropertyId())) {
				return resultMap;
			}
		}
		return null;
	}
	
	
	
}
