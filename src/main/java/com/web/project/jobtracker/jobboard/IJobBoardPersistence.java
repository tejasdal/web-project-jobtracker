package com.web.project.jobtracker.jobboard;

import java.util.List;

/**
 * @author Tejas Patel
 * Persistence interface containing persistence methods signature that will perform CRUD operation on jobboard of DB.
 */
public interface IJobBoardPersistence {
    JobBoard findById(Long jobBoardId);

    JobBoard save(JobBoard jobBoard);

    JobBoard update(JobBoard jobBoard);

    void delete(Long jobBoardId);

    List<JobBoard> getJobBoardByUserId(String userId);
}
