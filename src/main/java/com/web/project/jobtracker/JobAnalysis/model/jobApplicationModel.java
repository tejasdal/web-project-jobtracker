package com.web.project.jobtracker.JobAnalysis.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jobapplication")
public class jobApplicationModel {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String user_id;
	private String status_id;
	private Timestamp updated_date;
	public int getId() {
		return id;
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
	public String getStatus() {
		return status_id;
	}
	public void setStatus(String status) {
		this.status_id = status;
	}
	public Timestamp getDate() {
		return updated_date;
	}
	public void setDate(Timestamp date) {
		this.updated_date = date;
	}
}

