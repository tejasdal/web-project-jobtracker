package com.web.project.jobtracker.jobactivities;

import com.web.project.jobtracker.jobactivities.exception.JobActivitiesException;
import com.web.project.jobtracker.jobactivities.exception.JobActivitiesInvalidArgumentException;
import com.web.project.jobtracker.jobactivities.exception.JobActivitiesNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anudish Jinturkar
 * Controller to expose REST APIs to perform CRU operation on job activities.
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/job-activity")
public class JobActivitiesController {
    @Autowired
    private IJobActivitiesService jobActivitiesService;

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArrayList<JobActivities>> getAllCompletedJobJobActivities(@RequestParam("user_id") String userID) throws JobActivitiesInvalidArgumentException, JobActivitiesNotExistsException, JobActivitiesException
    {  return this.jobActivitiesService.getListOfListJobActivities(userID); }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  JobActivities insertActivity(@RequestBody JobActivities jobActivities) throws JobActivitiesInvalidArgumentException, JobActivitiesNotExistsException, JobActivitiesException
    { return  this.jobActivitiesService.insertJobActivity(jobActivities); }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobActivities updateActivity(@RequestBody JobActivities jobActivities) throws  JobActivitiesInvalidArgumentException, JobActivitiesNotExistsException, JobActivitiesException
    { return this.jobActivitiesService.updateJobActivity(jobActivities); }

}
