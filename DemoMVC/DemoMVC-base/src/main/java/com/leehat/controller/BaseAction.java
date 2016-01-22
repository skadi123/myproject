package com.leehat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.leehat.exception.ExceptionManager;
import com.leehat.pojo.User;
import com.leehat.service.BaseService;

@Controller
@RequestMapping("/base")
public class BaseAction {

	@Autowired
	private BaseService baseService;
	
	private Log logger = LogFactory.getLog(ExceptionManager.class);

	
	@RequestMapping(value = "/testBase",method = RequestMethod.GET)
	public void testBase(HttpServletRequest request,HttpServletResponse response){
		System.out.println("===========springmvc test suc!===========");
		List<User> list = baseService.queryUserInfo();
		for (User user : list) {
			System.out.println("name:"+user.getName());
		}
	}
	
	@RequestMapping(value = "/getParam",method = RequestMethod.POST)
	public void getParam(@RequestParam String username,HttpServletResponse response){
		System.out.println("===========begin to getParam!===========");
		System.out.println("username:"+username);
	}
	
	@RequestMapping(value = "/toPage",method = RequestMethod.GET)
	public String toPage(){
		System.out.println("===========toPage Test!===========");
		return "index";
	}
	
}
