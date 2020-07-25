package com.web.project.jobtracker.jobactivities;

import java.sql.Date;

/**
 * @author Anudish Jinturkar
 * Schema of a job activities.
 */

public class JobActivities {
    private int id;
    private String user_id;
    private Date date_created;
    private Date date_completed;
    private byte activity_status;
    private Date activity_deadline;
    private String activity_detail;

    public int getId(){ return id; }
    public void setId(int id){ this.id=id; }

    public String getUser_id(){ return  user_id; }
    public void setUser_id(String user_id){ this.user_id = user_id; }

    public Date getDate_created(){ return  date_created; }
    public void setDate_created(Date date_created){ this.date_created = date_created; }

    public Date getDate_completed(){ return  date_completed; }
    public void setDate_completed(Date date_completed){ this.date_completed = date_completed; }

    public byte getActivity_status() { return activity_status; }
    public void setActivity_status(byte activity_status){ this.activity_status = activity_status; }

    public Date getActivity_deadline() { return activity_deadline; }
    public void setActivity_deadline(Date activity_deadline) { this.activity_deadline = activity_deadline; }

    public String getActivity_detail() { return activity_detail; }
    public void setActivity_detail(String activity_detail) { this.activity_detail = activity_detail; }

}
