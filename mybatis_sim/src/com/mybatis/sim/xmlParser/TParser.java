package com.mybatis.sim.xmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.mybatis.sim.config.domain.Configuration;
import com.mybatis.sim.mapper.domain.Mapper;

public class TParser {
	
	/**
	 * 读取configuration.xml文件
	 * @param fileName 文件名及路径
	 * @return
	 * @throws JAXBException
	 */
	@SuppressWarnings("unchecked")
	public <T> T readConfigurationXml(String fileName) throws JAXBException {
		return (T) readXml(Configuration.class, fileName);
	}
	
	/**
	 * 读取mapper.xml文件
	 * @param fileName 文件名
	 * @return
	 * @throws JAXBException
	 */
	@SuppressWarnings("unchecked")
	public <T> T readMapperXml(String fileName) throws JAXBException {
		return (T) readXml(Mapper.class, fileName);
	}

	@SuppressWarnings("unchecked")
	public <T> T readXml(Class<T> clazz, String fileName) throws JAXBException {
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            return (T) u.unmarshal(TParser.class.getClassLoader().getResource(fileName));
        } catch (JAXBException e) {
            throw e;
        }
    }
	
}
