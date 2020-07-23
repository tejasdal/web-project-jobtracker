package com.web.project.jobtracker.jobactivities;

import com.web.project.jobtracker.jobactivities.exception.JobActivitiesException;
import com.web.project.jobtracker.jobactivities.exception.JobActivitiesInvalidArgumentException;
import com.web.project.jobtracker.jobactivities.exception.JobActivitiesNotExistsException;
import com.web.project.jobtracker.jobcontacts.JobContacts;
import com.web.project.jobtracker.jobcontacts.exception.JobContactsException;
import com.web.project.jobtracker.jobcontacts.exception.JobContactsInvalidArgumentException;
import com.web.project.jobtracker.jobcontacts.exception.JobContactsNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/job-activity")
public class JobActivitiesController {
    @Autowired
    private IJobActivitiesService jobActivitiesService;

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JobActivities> getAllJobJobActivities(){
        return this.jobActivitiesService.getAllJobActivities();
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  JobActivities insertActivity(@RequestBody JobActivities jobActivities) throws JobActivitiesInvalidArgumentException, JobActivitiesNotExistsException, JobActivitiesException
    { return  this.jobActivitiesService.insertJobActivity(jobActivities); }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobActivities updateActivity(@RequestBody JobActivities jobActivities) throws JobContactsNotExistsException, JobContactsInvalidArgumentException, JobContactsException {
        return this.jobActivitiesService.updateJobContacts(jobActivities);
    }

    @DeleteMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteActivities(@RequestParam("id") int ActivityID) throws JobContactsException, JobContactsInvalidArgumentException, JobContactsNotExistsException {
        this.jobActivitiesService.deleteJobActivities(ActivityID);
    }


}
