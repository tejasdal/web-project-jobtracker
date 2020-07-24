package com.web.project.jobtracker.jobapplication;

import com.web.project.jobtracker.jobapplication.exception.JobApplicationException;
import com.web.project.jobtracker.jobapplication.exception.JobApplicationInvalidArgumentException;
import com.web.project.jobtracker.jobapplication.exception.JobApplicationNotExistsException;

import java.util.List;
import java.util.Map;

/**
 * @author Tejas Patel
 * Service interface containing methods signature that will perform CRUD operation on job application.
 */
public interface IJobApplicationService {
    JobApplication getJobApplication(Long jobApplicationId) throws JobApplicationNotExistsException;

    Map<JobApplicationStatus, List<JobApplication>> getJobApplicationByJobBoard(Long jobBoardId);

    JobApplication saveJobApplication(JobApplication jobApplication) throws JobApplicationInvalidArgumentException, JobApplicationNotExistsException, JobApplicationException;

    JobApplication updateJobApplication(JobApplication jobApplication) throws JobApplicationInvalidArgumentException, JobApplicationNotExistsException, JobApplicationException;

    void deleteJobApplication(Long jobApplicationId) throws JobApplicationNotExistsException, JobApplicationException;
}
