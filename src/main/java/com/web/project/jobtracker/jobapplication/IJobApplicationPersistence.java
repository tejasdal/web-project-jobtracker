package com.web.project.jobtracker.jobapplication;

import java.util.List;

public interface IJobApplicationPersistence {
    JobApplication findById(Long jobApplicationId);

    JobApplication save(JobApplication jobApplication);

    JobApplication update(JobApplication jobApplication);

    void delete(Long jobApplicationId);

    List<JobApplication> findByJobBoardId(Long jobBoardId);
}
