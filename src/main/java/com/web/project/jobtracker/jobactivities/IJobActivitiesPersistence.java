package com.web.project.jobtracker.jobactivities;

import java.util.List;

public interface IJobActivitiesPersistence {
    List<JobActivities> getAllJobActivities(String userID);

    JobActivities insert(JobActivities jobActivities);

    JobActivities update(JobActivities jobActivities);

    JobActivities findById(int jobActivityId);
}
