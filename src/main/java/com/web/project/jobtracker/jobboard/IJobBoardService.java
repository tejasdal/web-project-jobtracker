package com.web.project.jobtracker.jobboard;

import com.web.project.jobtracker.jobboard.exception.JobBoardException;
import com.web.project.jobtracker.jobboard.exception.JobBoardInvalidArgumentException;
import com.web.project.jobtracker.jobboard.exception.JobBoardNotExistsException;

import java.util.List;

/**
 * @author Tejas Patel
 * Service interface containing methods signature that will perform CRUD operation on job board.
 */
public interface IJobBoardService {
    JobBoard getJobBoard(Long jobBoardId) throws JobBoardNotExistsException;

    JobBoard saveJobBoard(JobBoard jobBoard) throws JobBoardException, JobBoardInvalidArgumentException;

    JobBoard updateJobBoard(JobBoard jobBoard) throws JobBoardNotExistsException, JobBoardException, JobBoardInvalidArgumentException;

    void deleteJobBoard(Long jobBoardId) throws JobBoardNotExistsException, JobBoardException;

    JobBoard getJobBoardForUser(String userId) throws JobBoardException;
}
