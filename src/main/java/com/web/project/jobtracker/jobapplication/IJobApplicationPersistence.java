package com.web.project.jobtracker.jobapplication;

import java.util.List;

/**
 * @author Tejas Patel
 * Persistence interface containing persistence methods signature that will perform CRU operation on jobapplication of DB.
 */
public interface IJobApplicationPersistence {
    JobApplication findById(Long jobApplicationId);

    JobApplication save(JobApplication jobApplication);

    JobApplication update(JobApplication jobApplication);

    void delete(Long jobApplicationId);

    List<JobApplication> findByJobBoardId(Long jobBoardId);
}
