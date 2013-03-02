package com.prime;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ResizeEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

@ManagedBean(name="dashboardBean")
@SessionScoped
public class DashboardBean implements Serializable {

	private DashboardModel model;
	
	public DashboardBean() {
		model = new DefaultDashboardModel();
		
		DashboardColumn column1 = new DefaultDashboardColumn();
		DashboardColumn column2 = new DefaultDashboardColumn();
		DashboardColumn column3 = new DefaultDashboardColumn();
		
		column1.addWidget("sales");
		column1.addWidget("revenue");
		
		column2.addWidget("newconnection");
		column2.addWidget("disconnections");
		
		column3.addWidget("connection");

		model.addColumn(column1);
		model.addColumn(column2);
		model.addColumn(column3);
	}
	
	public void notification(){
	
	}
	
	public void handleResize(ResizeEvent event) {
		
		System.out.println("Handline Resize of : "+event.getComponent().getFamily());
	}
	
	public void handleReorder(DashboardReorderEvent event) {
		System.out.println("handleReorder of : "+event.getComponent());
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setSummary("Reordered: " + event.getWidgetId());
		message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());
		
		addMessage(message);
	}
	
	
	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public DashboardModel getModel() {
		return model;
	}
}
			