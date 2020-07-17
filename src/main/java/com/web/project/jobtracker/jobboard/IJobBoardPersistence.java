package com.web.project.jobtracker.jobboard;

import java.util.List;

public interface IJobBoardPersistence {
    JobBoard findById(Long jobBoardId);

    JobBoard save(JobBoard jobBoard);

    JobBoard update(JobBoard jobBoard);

    void delete(Long jobBoardId);

    List<JobBoard> getJobBoardByUserId(Long userId);
}
