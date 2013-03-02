package com.prime.user;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware{

	private static ApplicationContext ctx;
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		ctx = arg0;
		
	}
	
	public static Object getBean(String name){
		return ctx.getBean(name);
	}

}
