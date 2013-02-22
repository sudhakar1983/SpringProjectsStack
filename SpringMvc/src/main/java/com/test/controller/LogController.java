package com.test.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.LogManager;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;


@Controller
@RequestMapping(value="/logconsole/")
public class LogController {
	Logger log = LoggerFactory.getLogger(LogController.class);
	
	public static void main(String[] args) {
		
		LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
		List<ch.qos.logback.classic.Logger>  loglist = lc.getLoggerList();
		for(Logger log :loglist ){
			System.out.println(log.getName());
		}
		
	}
	

	
	
	@RequestMapping(value="show" , method=RequestMethod.GET)
	public String showConsole(Model model){
		List loggers = (List) getLoggers();
		List <MyPriority> priorities = (List<MyPriority>) getLevels() ;
		
		log.info("Log console accessed ");
		
		model.addAttribute("loggers", loggers);
		model.addAttribute("level", priorities);		
		String view ="logview";		
		
		log.debug("Loading view : "+view);
		return  view;
	}
	
	@RequestMapping(value="show" , method=RequestMethod.POST)
	public String saveLoggingLevel(Model model,HttpServletRequest request){		
		
		final Enumeration params = request.getParameterNames();
		ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		while (params.hasMoreElements()) {
			final String parameter = (String) params.nextElement();
			if (parameter.startsWith("cat.")) {
				
				final String logger = parameter.substring("cat.".length());
				final String level = request.getParameter(parameter);
				
				if (!"leave".equals(level)) {

					if ("root".equals(logger)) {												
						root.setLevel(Level.toLevel(level));
					} else {
						if ("none".equals(level)) {
							ch.qos.logback.classic.Logger logtemp =  (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(logger);
							logtemp.setLevel(null);
							
						} else {
							ch.qos.logback.classic.Logger logtemp =  (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(logger);
							logtemp.setLevel(Level.toLevel(level));							
						}
					}
				}
			}
		}
		
		
		List loggers = (List) getLoggers();
		List <MyPriority> priorities = (List<MyPriority>) getLevels() ;
		
		model.addAttribute("loggers", loggers);
		model.addAttribute("level", priorities);		
		String view ="redirect:/logconsole/show";	
		
		return  view;
	}
	
	
	
	private static List<Logger> getLoggers() {
		final Logger root = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);;
		final List result = new ArrayList();		
		result.add(root);
		
		LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
		result.addAll(lc.getLoggerList());
		return result;
	}

	private static Collection getLevels() {
		final List result = new ArrayList();
		final Level[] all = new Level[] { Level.DEBUG, Level.INFO, Level.WARN,
				Level.ERROR};
		for (int i = 0; i < all.length; i++) {
			result.add(new MyPriority(all[i]));
		}
		return result;
	}

	public static class MyPriority {
		Level _level;

		public MyPriority(final Level level) {
			_level = level;
		}

		public String getName() {
			return _level.toString();
		}
	}
}
