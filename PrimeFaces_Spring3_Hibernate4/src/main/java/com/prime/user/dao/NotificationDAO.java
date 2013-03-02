package com.prime.user.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.prime.model.Notification;

@Component("notificationDAO")
@Transactional
public class NotificationDAO implements INotificationDAO {

	@Autowired
	private SessionFactory sessionFactory;
	

	/**
	 * Get Hibernate Session Factory
	 * 
	 * @return SessionFactory - Hibernate Session Factory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Set Hibernate Session Factory
	 * 
	 * @param SessionFactory - Hibernate Session Factory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Override
	public void addNotification(Notification Notification) {
		getSessionFactory().getCurrentSession().save(Notification);

	}

	@Override
	public void updateNotification(Notification Notification) {
		getSessionFactory().getCurrentSession().update(Notification);

	}

	@Override
	public void deleteNotification(Notification Notification) {
		getSessionFactory().getCurrentSession().delete(Notification);

	}

	@Override
	public Notification getNotificationById(int id) {
		
		return (Notification) getSessionFactory().getCurrentSession().get(Notification.class, id);
	}

	@Override
	public List<Notification> getNotifications() {
		List<Notification> ns = (List<Notification>) getSessionFactory().getCurrentSession().createQuery("from Notification").list();
		return ns;
	}
	
	@Override
	public List<Notification> getNotificationsUnRead() {
		List<Notification> ns = (List<Notification>) getSessionFactory().getCurrentSession().createQuery("from Notification where read=0").list();
		//getSessionFactory().getCurrentSession().createQuery("update Notification set read=1 where read=0 ").executeUpdate();
		return ns;
	}

}
