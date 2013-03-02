package com.prime.user.dao;

import java.util.List;

import com.prime.model.Notification;

public interface INotificationDAO {

	/**
	 * Add Notification
	 * 
	 * @param  Notification Notification
	 */
	public void addNotification(Notification Notification);
	
	/**
	 * Update Notification
	 * 
	 * @param  Notification Notification
	 */
	public void updateNotification(Notification Notification);
	
	/**
	 * Delete Notification
	 * 
	 * @param  Notification Notification
	 */
	public void deleteNotification(Notification Notification);
	
	/**
	 * Get Notification
	 * 
	 * @param  int Notification Id
	 */
	public Notification getNotificationById(int id);
	
	/**
	 * Get Notification List
	 * 
	 */
	public List<Notification> getNotifications();

	List<Notification> getNotificationsUnRead();
}
