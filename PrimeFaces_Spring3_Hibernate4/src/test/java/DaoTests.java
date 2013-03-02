import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.prime.model.Notification;
import com.prime.user.dao.INotificationDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Transactional
@TransactionConfiguration(defaultRollback=false)
public class DaoTests {

	@Autowired
	private INotificationDAO notificationDAO;
	
	@Test
	public void getNotifications(){
		List<Notification>  ns = notificationDAO.getNotificationsUnRead();
		
		for(Notification n: ns){
			System.out.println(n);
		}
	}
	
	@Test
	public void getNotification(){
		System.out.println(notificationDAO.getNotificationById(1));
		Notification notification = notificationDAO.getNotificationById(1);
		notification.setRead(true);
		notificationDAO.updateNotification(notification);
		System.out.println(notificationDAO.getNotificationById(1));
	}
}
