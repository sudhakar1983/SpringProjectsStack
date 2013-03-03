package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prime.model.Notification;
import com.prime.user.ApplicationContextUtil;
import com.prime.user.dao.INotificationDAO;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

/**
 * Servlet implementation class NotificationRssServlet
 */
public class NotificationRssServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificationRssServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		INotificationDAO notificationDAO=  (INotificationDAO) ApplicationContextUtil.getBean("notificationDAO");
		List<Notification>  ns = notificationDAO.getNotificationsUnRead();
		
		response.setContentType("text/xml");		
		Writer writer = response.getWriter();
		
		
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType("rss_2.0"); 
		feed.setTitle("Notification"); 
		feed.setLink("http://localhost:8080/prime/notification/rss");
		feed.setDescription("Notification feed");
/*		ArrayList<String> categories = new ArrayList<String>();
		categories.add("notifications");*/
		
		List<SyndCategory> entryCategories = new ArrayList<SyndCategory>();
		SyndCategory category = new SyndCategoryImpl();
		category.setName("MyProject"); 
		entryCategories.add(category);
	
		
		feed.setCategories(entryCategories);
		
		
		List<SyndEntry> entryList = new ArrayList<SyndEntry>();
		
			
		for(Notification n: ns){
			System.out.println(n);
			
			SyndEntry entry = new SyndEntryImpl(); 
			entry.setTitle("NotificationId:"+n.getId()); 
			entry.setLink("");
			entry.setPublishedDate(new Date()) ;
					
			SyndContent description = new SyndContentImpl();
			description.setType("text/html");
			description.setValue("<p>" +
					"<b>Notification Id</b>: "+n.getId() +
					"<b>Notification Description</b>: "+n.getNotificationMessage() +
					"<b>POsted by User Id</b>: "+n.getUser().getId() +
					"<b>Name </b>: "+n.getUser().getName() +
					"<b>Surname </b>: "+n.getUser().getSurname() +			
					"</p>");
			entry.setDescription(description);
			

			entry.setCategories(entryCategories);
			entryList.add(entry);			
		}
		
		
		
		feed.setEntries(entryList);
        SyndFeedOutput output = new SyndFeedOutput(); 
        try {
			output.output(feed,writer);
		} catch (FeedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
