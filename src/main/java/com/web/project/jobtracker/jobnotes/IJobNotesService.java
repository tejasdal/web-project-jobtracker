package com.web.project.jobtracker.jobnotes;

import com.web.project.jobtracker.jobnotes.exception.JobNotesException;
import com.web.project.jobtracker.jobnotes.exception.JobNotesInvalidArgumentException;
import com.web.project.jobtracker.jobnotes.exception.JobNotesNotExistsException;

import java.util.List;

/**
 * @author Anudish Jinturkar
 * Service interface containing methods signature that will perform CRUD operation on job notes.
 */

public interface IJobNotesService {
    List<JobNotes> getAllJobNotes(String userID) throws JobNotesNotExistsException, JobNotesException, JobNotesInvalidArgumentException;

    JobNotes saveJobNotes(JobNotes jobNotes) throws JobNotesNotExistsException, JobNotesException, JobNotesInvalidArgumentException;

    JobNotes updateJobNotes(JobNotes jobNotes) throws JobNotesNotExistsException, JobNotesException, JobNotesInvalidArgumentException;

    void deleteJobNotes(int jobNotesId) throws JobNotesNotExistsException, JobNotesException, JobNotesInvalidArgumentException;

}
