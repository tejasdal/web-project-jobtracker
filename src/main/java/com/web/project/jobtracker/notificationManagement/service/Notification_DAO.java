package com.web.project.jobtracker.notificationManagement.service;


public class Notification_DAO {
	
	String data;
	String deadline;

	public Notification_DAO(){
		
	}
	
	public Notification_DAO(String data, String deadline) {
		super();
		this.data = data;
		this.deadline = deadline;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	

}
