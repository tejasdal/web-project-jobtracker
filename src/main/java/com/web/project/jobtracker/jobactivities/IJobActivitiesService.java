package com.web.project.jobtracker.jobactivities;

import com.web.project.jobtracker.jobactivities.exception.JobActivitiesException;
import com.web.project.jobtracker.jobactivities.exception.JobActivitiesInvalidArgumentException;
import com.web.project.jobtracker.jobactivities.exception.JobActivitiesNotExistsException;
import com.web.project.jobtracker.jobnotes.exception.JobNotesNotExistsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Anudish Jinturkar
 * Service interface containing methods signature that will perform CRU operation on job activities.
 */

public interface IJobActivitiesService {

    JobActivities insertJobActivity(JobActivities jobActivities) throws JobActivitiesException, JobActivitiesInvalidArgumentException, JobActivitiesNotExistsException;

    JobActivities updateJobActivity(JobActivities jobActivities) throws JobActivitiesException, JobActivitiesInvalidArgumentException, JobActivitiesNotExistsException;

    List<ArrayList<JobActivities>> getListOfListJobActivities(String userID) throws JobActivitiesNotExistsException;
}
