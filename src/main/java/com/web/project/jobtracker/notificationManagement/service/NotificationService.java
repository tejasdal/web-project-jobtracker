package com.web.project.jobtracker.notificationManagement.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.project.jobtracker.notificationManagement.repository.NotificationRepository;


@Service
public class NotificationService extends Notification_DAO {

	@Autowired
	NotificationRepository notificationRepository;
	
	Notification_DAO obj_Dao;
	
	public List<Notification_DAO> getDeadlines(String user_id)
	{
		
		Date today = new Date();               
		SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");       

		Calendar c = Calendar.getInstance();        
		LocalDate current_date=LocalDate.now();
		c.add(Calendar.DATE, 7);  // number of days to add      
		String week_after_date = (String)(formattedDate.format(c.getTime()));
		List<String> dataList=new ArrayList<String>();
		List<String> deadline=new ArrayList<String>();
		
		ArrayList<Notification_DAO> obj_dao=new ArrayList<Notification_DAO>();
		dataList=notificationRepository.NotificationData(current_date.toString(), week_after_date, user_id);
		deadline=notificationRepository.NotificationDeadline(current_date.toString(), week_after_date, user_id);
		
		for(int i=0;i<dataList.size();i++)
		{
			obj_Dao=new Notification_DAO(dataList.get(i),deadline.get(i));
			obj_dao.add(obj_Dao);
		}
		return obj_dao;
	}
	
	
}
