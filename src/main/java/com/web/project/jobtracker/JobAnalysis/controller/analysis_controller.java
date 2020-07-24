package com.web.project.jobtracker.JobAnalysis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.web.project.jobtracker.JobAnalysis.service.WrittenBlogAnalysisService;
import com.web.project.jobtracker.JobAnalysis.service.JobActivityService;
import com.web.project.jobtracker.JobAnalysis.service.JobApplicationDataService;

@RestController
@CrossOrigin(origins = "*")
public class analysis_controller {

	@Autowired
	JobApplicationDataService service;

	@Autowired
	WrittenBlogAnalysisService writtenBlodService;
	
	@Autowired
	JobActivityService jobActivityService;

	@GetMapping("jobAnalysis/application_status_count/{month}/{user_id}")
	public List<Integer> count(@PathVariable Integer month, @PathVariable String user_id) {
		System.out.println(user_id);
		System.out.println("Request came for --->" + month);
		return service.getcount(month, user_id);
	}

	@GetMapping("jobAnalysis/blogs_count/{user_id}")
	public List<Integer> count_written_blog(@PathVariable String user_id) {
		System.out.println("Method called for the user about written blog");
		return writtenBlodService.getWrittenBlogData(user_id);
	}

	@GetMapping("jobAnalysis/job_activity/{month}/{user_id}")
	public List<List<Integer>> count_job_activity(@PathVariable String user_id,@PathVariable Integer month)
	{
		System.out.println("Method called for the user about job activity");
		return jobActivityService.get_all_activity_count(user_id, month);
	}
}
