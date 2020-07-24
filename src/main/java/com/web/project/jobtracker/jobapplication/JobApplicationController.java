package com.web.project.jobtracker.jobapplication;

import com.web.project.jobtracker.jobapplication.exception.JobApplicationException;
import com.web.project.jobtracker.jobapplication.exception.JobApplicationInvalidArgumentException;
import com.web.project.jobtracker.jobapplication.exception.JobApplicationNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Tejas Patel
 * Controller to expose REST APIs to perform CRUD operation on job application.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/job-application")
public class JobApplicationController {

    @Autowired
    private IJobApplicationService jobApplicationService;

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobApplication getJobApplication(@RequestParam("id") Long jobApplicationId) throws JobApplicationNotExistsException {
        return this.jobApplicationService.getJobApplication(jobApplicationId);
    }

    @GetMapping(value = "/job-board", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<JobApplicationStatus, List<JobApplication>> getJobApplicationByJobBoard(@RequestParam("id") Long jobBoardId){
        return this.jobApplicationService.getJobApplicationByJobBoard(jobBoardId);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobApplication saveJobApplication(@RequestBody JobApplication jobApplication) throws JobApplicationInvalidArgumentException, JobApplicationNotExistsException, JobApplicationException {
        return this.jobApplicationService.saveJobApplication(jobApplication);
    }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobApplication updateJobApplication(@RequestBody JobApplication jobApplication) throws JobApplicationInvalidArgumentException, JobApplicationNotExistsException, JobApplicationException {
        return this.jobApplicationService.updateJobApplication(jobApplication);
    }

    @DeleteMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteJobApplication(@RequestParam("id") Long jobApplicationId) throws JobApplicationNotExistsException, JobApplicationException {
        System.out.println("Application ID" + jobApplicationId);
        this.jobApplicationService.deleteJobApplication(jobApplicationId);
    }
}
