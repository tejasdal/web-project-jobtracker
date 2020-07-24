package com.web.project.jobtracker.JobAnalysis.service;


import java.util.ArrayList;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.project.jobtracker.JobAnalysis.repository.JobApplicationRepository;
import com.web.project.jobtracker.JobAnalysis.model.jobApplicationModel;

@Service
public class JobApplicationDataService {
	
	@Autowired
	JobApplicationRepository repository;
	
	public List<jobApplicationModel> getallData() {
		//return 	 topicRepository.findAllActiveUsersNative();
		return repository.findAllActiveUsersNative();
	}
	
	public List<Integer> getcount(int month,String user_id)
	{
		String date ="2019-01-14";
		DateTime dt;
		System.out.println("service class"+user_id);
		DateTimeFormatter fmt;

		dt = new DateTime().minusMonths(month);
		fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
		date = fmt.print(dt);
		

		List<Integer> list=new ArrayList<Integer>();
		list.add(repository.findCount(1,date,user_id));
		list.add(repository.findCount(2,date,user_id));
		list.add(repository.findCount(3,date,user_id));
		list.add(repository.findCount(4,date,user_id));
		list.add(repository.findCount(5,date,user_id));
		System.out.println("Output is"+ list);
		return list;
	}
	
	

}
