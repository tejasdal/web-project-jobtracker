package com.web.project.jobtracker.JobAnalysis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.project.jobtracker.JobAnalysis.repository.WrittenBlogAnalysisRepo;
@Service
public class WrittenBlogAnalysisService {

	
	@Autowired
	WrittenBlogAnalysisRepo repository;
	
	
	public List<Integer> getWrittenBlogData(String user_id)
	{
		List<Integer> list=new ArrayList<Integer>();
		
		
		for(int i=1;i<13;i++)
		{
			list.add(repository.findWrittenCount(2020, i, user_id));
		}
		System.out.println(list);
		
		return list;
	}
}
