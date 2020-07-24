package com.web.project.jobtracker.notificationManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.web.project.jobtracker.notificationManagement.service.NotificationService;
import com.web.project.jobtracker.notificationManagement.service.Notification_DAO;


@RestController
@CrossOrigin(origins="*")
public class NotificationController {

	@Autowired
	NotificationService notificationService;
	
	@GetMapping("Notifications/getdeadline/{user_id}")
	
	public List<Notification_DAO> getDeadline(@PathVariable String user_id)
	{
		System.out.println("Mrthod of notificaiton is called");
		return notificationService.getDeadlines(user_id);
	}
	
}
