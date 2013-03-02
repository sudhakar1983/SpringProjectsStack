package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.prime.model.Notification;
import com.prime.user.ApplicationContextUtil;
import com.prime.user.dao.INotificationDAO;

/**
 * Servlet implementation class NotificationServlet
 */

public class NotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static AtomicInteger i = new AtomicInteger(0);

	/**
     * Default constructor. 
     */
    public NotificationServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		INotificationDAO notificationDAO=  (INotificationDAO) ApplicationContextUtil.getBean("notificationDAO");
		
		response.setContentType("application/json");
		
		PrintWriter pw = response.getWriter();
		
		String userId = request.getParameter("userid");
		String notificationid = request.getParameter("notificationid");
		
		System.out.println("notification :"+notificationid);
		if(null != userId && null != notificationid && !userId.trim().equals("")){
			System.out.println("updating the notification :"+notificationid);
			
			Notification notification = notificationDAO.getNotificationById(Integer.parseInt(notificationid));
			notification.setRead(true);
			notificationDAO.updateNotification(notification);
			pw.print("success");
		}else{
			int val = 0;
			synchronized (this) {
				val = i.addAndGet(1);
			}
			
			List<Notification> ns = notificationDAO.getNotificationsUnRead();
			
			Gson gson = new Gson();
			String json = gson.toJson(ns);
			pw.print(json);
		}
		

		
	
	
		pw.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
