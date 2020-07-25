package com.web.project.jobtracker.jobactivities;

import java.util.List;

/**
 * @author Anudish Jinturkar
 * Persistence interface containing persistence methods signature that will perform CRU operation on job activities of DB.
 */

public interface IJobActivitiesPersistence {
    List<JobActivities> getAllJobActivities(String userID);

    JobActivities insert(JobActivities jobActivities);

    JobActivities update(JobActivities jobActivities);

    JobActivities findById(int jobActivityId);
}
