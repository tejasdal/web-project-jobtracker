package com.web.project.jobtracker.jobapplication;

import com.web.project.jobtracker.jobapplication.exception.JobApplicationException;
import com.web.project.jobtracker.jobapplication.exception.JobApplicationInvalidArgumentException;
import com.web.project.jobtracker.jobapplication.exception.JobApplicationNotExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Tejas Patel
 * Service class containing bussiness logic to perform CRUD operation on job application.
 */
@Service
public class JobApplicationService implements IJobApplicationService{

    Logger log = LoggerFactory.getLogger(JobApplicationService.class);

    @Autowired
    private IJobApplicationPersistence jobApplicationPersistence;

    @Override
    public JobApplication getJobApplication(Long jobApplicationId) throws JobApplicationNotExistsException {

        JobApplication jobApplication = this.jobApplicationPersistence.findById(jobApplicationId);
        if( null == jobApplication){
            log.warn("Job Application with ID: {} does not exists in the database.", jobApplicationId);
            throw new JobApplicationNotExistsException(jobApplicationId);
        }
        return jobApplication;
    }

    @Override
    public Map<JobApplicationStatus, List<JobApplication>> getJobApplicationByJobBoard(Long jobBoardId) {
        List<JobApplication> jobApplications = this.jobApplicationPersistence.findByJobBoardId(jobBoardId);
        return this.filterByStatus(jobApplications);
    }

    private Map<JobApplicationStatus, List<JobApplication>> filterByStatus(List<JobApplication> jobApplications) {
        Map<JobApplicationStatus, List<JobApplication>> jobApplicationsByStatus = new HashMap<>();
        jobApplicationsByStatus.put(JobApplicationStatus.WHISHLIST, new ArrayList<>());
        jobApplicationsByStatus.put(JobApplicationStatus.APPLIED, new ArrayList<>());
        jobApplicationsByStatus.put(JobApplicationStatus.INTERVIEW, new ArrayList<>());
        jobApplicationsByStatus.put(JobApplicationStatus.OFFER, new ArrayList<>());
        jobApplicationsByStatus.put(JobApplicationStatus.REJECT, new ArrayList<>());

        if(null != jobApplications){
            for (JobApplication jobApplication: jobApplications) {
                if (jobApplicationsByStatus.containsKey(jobApplication.getStatus())){
                    jobApplicationsByStatus.get(jobApplication.getStatus()).add(jobApplication);
                }
            }
        }
        return jobApplicationsByStatus;
    }

    @Override
    public JobApplication saveJobApplication(JobApplication jobApplication) throws JobApplicationInvalidArgumentException,
            JobApplicationNotExistsException, JobApplicationException {

        this.validateJobApplication(jobApplication);
        try{
            jobApplication.setCreatedAt(new Timestamp(new Date().getTime()));
            jobApplication.setUpdatedAt(new Timestamp(new Date().getTime()));
            System.out.println(jobApplication.getStatus());
            return this.jobApplicationPersistence.save(jobApplication);
        }catch (Exception e){
            log.error("Error while saving new job application with ID: {} in the database.", jobApplication.getId());
            throw new JobApplicationException("Error while saving new job application with ID: " + jobApplication.getId()+
                    " in the database.", e);
        }
    }

    private void validateJobApplication(JobApplication jobApplication) throws JobApplicationInvalidArgumentException {

        if (null == jobApplication){
            log.warn("Invalid job application argument.");
            throw new JobApplicationInvalidArgumentException();
        }
    }

    @Override
    public JobApplication updateJobApplication(JobApplication jobApplication) throws JobApplicationInvalidArgumentException,
            JobApplicationNotExistsException, JobApplicationException {

        this.validateJobApplication(jobApplication);
        this.getJobApplication(jobApplication.getId());
        try{
            jobApplication.setUpdatedAt(new Timestamp(new Date().getTime()));
            return this.jobApplicationPersistence.update(jobApplication);
        }catch (Exception e){
            log.error("Error while updating job application with ID: {} in the database.", jobApplication.getId());
            throw new JobApplicationException("Error while updating job application with ID: " + jobApplication.getId()+
                    " in the database.", e);
        }
    }

    @Override
    public void deleteJobApplication(Long jobApplicationId) throws JobApplicationNotExistsException, JobApplicationException {

        this.getJobApplication(jobApplicationId);
        try{
            this.jobApplicationPersistence.delete(jobApplicationId);
        }catch (Exception e){
            log.error("Error while deleting job application with ID: {} from the database.", jobApplicationId);
            throw new JobApplicationException("Error while deleting job application with ID: " + jobApplicationId+
                    " from the database.", e);
        }
    }
}
