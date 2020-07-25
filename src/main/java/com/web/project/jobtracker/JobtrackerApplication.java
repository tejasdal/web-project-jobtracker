package com.web.project.jobtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@CrossOrigin("*")
@ComponentScan(basePackages = {"com.web.project.jobtracker.configurations"
		,"com.web.project.jobtracker.jobapplication"
		,"com.web.project.jobtracker.jobboard"
		,"com.web.project.jobtracker.jobcontacts"
		,"com.web.project.jobtracker.jobnotes"
		,"com.web.project.jobtracker.usermanagement",
		"com.web.project.jobtracker.JobAnalysis",

		"com.web.project.jobtracker.notificationManagement"
		,"com.web.project.jobtracker.jobactivities"})
public class JobtrackerApplication {
	public static void main(String[] args) {
		SpringApplication.run(JobtrackerApplication.class, args);
	}
}
