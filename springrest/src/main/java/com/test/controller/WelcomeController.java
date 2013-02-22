package com.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.app.User;


@Controller
@RequestMapping("/welcome")
public class WelcomeController {
	
	private static final Logger log = LoggerFactory.getLogger(WelcomeController.class) ;


	
	@RequestMapping(value="/home" ,method=RequestMethod.GET)
	/***
	 * To support HTTP PUT requests, the spring-web module provides the filter HttpPutFormContentFilter, which can be configured in web.xml:
	 * 
			<filter>
			  <filter-name>httpPutFormFilter</filter-name>
			  <filter-class>org.springframework.web.filter.HttpPutFormContentFilter</filter-class>
			</filter>
			<filter-mapping>
			  <filter-name>httpPutFormFilter</filter-name>
			  <servlet-name>dispatcherServlet</servlet-name>
			</filter-mapping>			
	 * 
	 * 
	 * **/

	//
	public User home(ModelMap model, @RequestParam(value="q",required=false)String q ,@RequestHeader("Accept-Encoding") String encoding
				){
		String view="home";
		
		log.debug("encoding :"+encoding);		
		User user = new User();
		user.setName("Sudhakar");
		user.setAge(25);
		model.addAttribute("user", user);
		return user;
	}

}
