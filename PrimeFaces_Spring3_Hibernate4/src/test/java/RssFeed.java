import java.io.FileWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prime.model.Notification;
import com.prime.user.dao.INotificationDAO;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:applicationContext.xml")
public class RssFeed {
	
	@Autowired
	private INotificationDAO notificationDAO;
	
	public static void main(String[] args) throws Exception {


	}
	
	@Test
	public  void rssGenerator(){
		List<Notification>  ns = notificationDAO.getNotificationsUnRead();
		
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
		
		System.out.println(feed);
	
	}
	
	public static void rssReader() throws Exception {

		URL rssUrl =  RssFeed.class.getClassLoader().getResource("topstories.xml");
		
		
		//URL feedSource = new URL("http://news.yahoo.com/rss/topstories");
        SyndFeedInput input = new SyndFeedInput();
        XmlReader xmlReader  =   new XmlReader(rssUrl);
        
        
        SyndFeed feed = input.build(xmlReader);
        
        System.out.println(feed);
        /*
        Writer writer = new FileWriter("stream.xml"); 
        SyndFeedOutput output = new SyndFeedOutput(); 
        output.output(feed,writer); 
        writer.close(); 
        */
        
        
	}
}
