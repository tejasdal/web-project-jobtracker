package com.web.project.jobtracker.jobnotes;

import com.web.project.jobtracker.jobnotes.exception.JobNotesException;
import com.web.project.jobtracker.jobnotes.exception.JobNotesInvalidArgumentException;
import com.web.project.jobtracker.jobnotes.exception.JobNotesNotExistsException;

import java.util.List;

public interface IJobNotesService {
    List<JobNotes> getAllJobNotes(String userID) throws JobNotesNotExistsException, JobNotesException, JobNotesInvalidArgumentException;

    JobNotes saveJobNotes(JobNotes jobNotes) throws JobNotesNotExistsException, JobNotesException, JobNotesInvalidArgumentException;

    JobNotes updateJobNotes(JobNotes jobNotes) throws JobNotesNotExistsException, JobNotesException, JobNotesInvalidArgumentException;

    void deleteJobNotes(int jobNotesId) throws JobNotesNotExistsException, JobNotesException, JobNotesInvalidArgumentException;

}
