package com.web.project.jobtracker.JobAnalysis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.project.jobtracker.JobAnalysis.repository.JobActivityRepository;

@Service
public class JobActivityService {

	
	@Autowired
	JobActivityRepository repositoyry;
	
	public List<List<Integer>> get_all_activity_count(String user_id,int month)
	{
		List<List<Integer>> main_List=new ArrayList<List<Integer>>();
		List<Integer> sub_list=new ArrayList<Integer>();
		List<Integer> sub_list01=new ArrayList<Integer>();
		
		for(int i=1;i<month+1;i++)
		{
			sub_list.add(repositoyry.pendingActivity(2020, i, user_id));
		}
		
		System.out.println("Pending activities are: ==>"+sub_list);
		main_List.add(sub_list);
		
		for(int i=1;i<month+1;i++)
		{
			sub_list01.add(repositoyry.completedActivity(2020, i, user_id));
		}
		
		main_List.add(sub_list01);
		System.out.println("Completed activities are: ==>"+sub_list01);
		
		return main_List;
	}
}
