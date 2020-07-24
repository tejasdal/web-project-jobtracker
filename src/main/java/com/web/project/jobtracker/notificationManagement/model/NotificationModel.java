package com.web.project.jobtracker.notificationManagement.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="activity")
public class NotificationModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String user_id;
	private Timestamp date_created;
	private Timestamp date_completed;
	private boolean activity_status;
	private String activity_detail;
	public int getId() {
		return id;
	}
	public String getActivity_detail() {
		return activity_detail;
	}
	public void setActivity_detail(String activity_detail) {
		this.activity_detail = activity_detail;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Timestamp getDate_created() {
		return date_created;
	}
	public void setDate_created(Timestamp date_created) {
		this.date_created = date_created;
	}
	public Timestamp getDate_completed() {
		return date_completed;
	}
	public void setDate_completed(Timestamp date_completed) {
		this.date_completed = date_completed;
	}
	public boolean isActivity_status() {
		return activity_status;
	}
	public void setActivity_status(boolean activity_status) {
		this.activity_status = activity_status;
	}
	

}
