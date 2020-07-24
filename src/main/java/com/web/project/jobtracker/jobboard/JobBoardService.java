package com.web.project.jobtracker.jobboard;

import com.web.project.jobtracker.jobboard.exception.JobBoardException;
import com.web.project.jobtracker.jobboard.exception.JobBoardInvalidArgumentException;
import com.web.project.jobtracker.jobboard.exception.JobBoardNotExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Tejas Patel
 * Service class containing bussiness logic to perform CRUD operation on job board.
 */
@Service
public class JobBoardService implements IJobBoardService {

    Logger log = LoggerFactory.getLogger(JobBoardService.class);

    @Autowired
    private IJobBoardPersistence jobBoardPersistence;

    @Override
    public JobBoard getJobBoard(Long jobBoardId) throws JobBoardNotExistsException {

        JobBoard jobBoard = this.jobBoardPersistence.findById(jobBoardId);
        if(null == jobBoard){
            log.warn("Job Board with ID: {} does not exist in the database.", jobBoardId);
            throw new JobBoardNotExistsException("Invalid ID: "+ jobBoardId +"for Job Board. Please provide valid ID.");
        }
        return jobBoard;
    }

    @Override
    public JobBoard saveJobBoard(JobBoard jobBoard) throws JobBoardInvalidArgumentException, JobBoardException {

        this.validateJobBoardArgument(jobBoard);
        try {
            jobBoard.setCreatedAt(new Timestamp(new Date().getTime()));
            jobBoard.setUpdatedAt(new Timestamp(new Date().getTime()));
            return this.jobBoardPersistence.save(jobBoard);
        }catch (Exception e){
            log.error("Error while saving a new job board in the database. Error: {}", e);
            throw new JobBoardException("Error while saving job board in the database.", e);
        }
    }

    private void validateJobBoardArgument(JobBoard jobBoard) throws JobBoardInvalidArgumentException {
        if(null == jobBoard){
            log.warn("Invalid job board argument.");
            throw new JobBoardInvalidArgumentException("Please provide valid job board data.");
        }
    }

    @Override
    public JobBoard updateJobBoard(JobBoard jobBoard) throws JobBoardNotExistsException, JobBoardException, JobBoardInvalidArgumentException {
        this.validateJobBoardArgument(jobBoard);
        this.getJobBoard(jobBoard.getId());
        try{
            jobBoard.setUpdatedAt(new Timestamp(new Date().getTime()));
            return this.jobBoardPersistence.update(jobBoard);
        }catch (Exception e){
            log.error("Error while updating a job board with ID: {} in the database. Error: {}", jobBoard.getId(), e);
            throw new JobBoardException("Error while updating a job board with ID: " + jobBoard.getId() + " in the database.", e);
        }
    }

    @Override
    public void deleteJobBoard(Long jobBoardId) throws JobBoardNotExistsException, JobBoardException {

        this.getJobBoard(jobBoardId);
        try{
            this.jobBoardPersistence.delete(jobBoardId);
        }catch (Exception e){
            log.error("Error while deleting a job board with ID: {} from the database. Error: {}", jobBoardId, e);
            throw new JobBoardException("Error while deleting a job board with ID: " + jobBoardId + " from the database.", e);
        }
    }

    @Override
    public JobBoard getJobBoardForUser(String userId) throws JobBoardException {
        try{
            List<JobBoard> jobBoards = this.jobBoardPersistence.getJobBoardByUserId(userId);
            if(null != jobBoards && jobBoards.size() > 0){
                return jobBoards.get(0);
            }else{
                JobBoard newJobBoard = new JobBoard();
                newJobBoard.setUserId(userId);
                newJobBoard.setName("My New Job Board!");
                return this.saveJobBoard(newJobBoard);
            }
        }catch (Exception e){
            log.error("Error while fetching job boards for user with ID: {} from the database. Error: {}", userId, e);
            throw new JobBoardException("Error while fetching job boards for user with ID: " + userId + " in the database.", e);
        }
    }
}
