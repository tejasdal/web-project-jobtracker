package com.web.project.jobtracker.jobactivities;

import java.util.List;

public interface IJobActivitiesService {
    List<JobActivities> getAllJobActivities();

    JobActivities insertJobActivity(JobActivities jobActivities);

    JobActivities updateJobContacts(JobActivities jobActivities);

    void deleteJobActivities(int activityID);
}
