package com.web.project.jobtracker.jobactivities;

import com.web.project.jobtracker.jobactivities.exception.JobActivitiesException;
import com.web.project.jobtracker.jobactivities.exception.JobActivitiesInvalidArgumentException;
import com.web.project.jobtracker.jobactivities.exception.JobActivitiesNotExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anudish Jinturkar
 * Service class containing bussiness logic to perform CRU operation on job activities.
 */

@Service
public class JobActivitiesService implements IJobActivitiesService{

    Logger log = LoggerFactory.getLogger(JobActivitiesService.class);

    @Autowired
    IJobActivitiesPersistence jobActivitiesPersistence;

    @Override
    public List<ArrayList<JobActivities>> getListOfListJobActivities(String userID) throws JobActivitiesNotExistsException {
        List<ArrayList<JobActivities>> newlist = new ArrayList<ArrayList<JobActivities>>();
        try {
            List<JobActivities> jobActivitiesList = new ArrayList<JobActivities>();
            List<JobActivities> toDoItemsList = new ArrayList<JobActivities>();
            List<JobActivities> completedItemsList = new ArrayList<JobActivities>();

            jobActivitiesList = jobActivitiesPersistence.getAllJobActivities(userID);
            for(JobActivities ja : jobActivitiesList){
                if(ja.getActivity_status() == 0 && ja.getDate_completed() == null){
                    toDoItemsList.add(ja);
                }
                else if(ja.getActivity_status() == 1 && ja.getDate_completed() != null) {
                    completedItemsList.add(ja);
                }
            }
            newlist.add((ArrayList<JobActivities>) toDoItemsList);
            newlist.add((ArrayList<JobActivities>) completedItemsList);

        }catch (Exception e){
            log.error("Error while getting the Activity with UserID: {} in the database.",userID);
            throw new JobActivitiesNotExistsException("Error while getting Activity with UserID: "+ userID +" in the database.", e);
        }
        return newlist;
    }

    @Override
    public JobActivities insertJobActivity(JobActivities jobActivities) throws JobActivitiesException, JobActivitiesNotExistsException, JobActivitiesInvalidArgumentException
    {
        this.validateJobActivities(jobActivities);
        try{
           return this.jobActivitiesPersistence.insert(jobActivities);
        }catch (Exception e){
            log.error("Error while inserting the Activity with ActivityID: {} in the database.",jobActivities.getId());
            throw new JobActivitiesNotExistsException("Error while inserting Activity with ID: "+ jobActivities.getId() +" in the database.", e);
        }
    }

    @Override
    public JobActivities updateJobActivity(JobActivities jobActivities) throws JobActivitiesException, JobActivitiesNotExistsException, JobActivitiesInvalidArgumentException
    {
        this.validateJobActivities(jobActivities);
        try {
            return this.jobActivitiesPersistence.update(jobActivities);
        }catch (Exception e){
            log.error("Error while updating the Activity with ActivityID: {} in the database.",jobActivities.getId());
            throw new JobActivitiesNotExistsException("Error while updating Activity with ID: "+ jobActivities.getId() +" in the database.", e);
        }
    }

    private void validateJobActivities(JobActivities jobActivities) throws JobActivitiesInvalidArgumentException {
        if(null == jobActivities){
            log.warn("Invalid Job Activities argument.");
            throw new JobActivitiesInvalidArgumentException();
        }
    }
}
