package com.web.project.jobtracker.jobnotes;

import com.web.project.jobtracker.jobnotes.exception.JobNotesException;
import com.web.project.jobtracker.jobnotes.exception.JobNotesInvalidArgumentException;
import com.web.project.jobtracker.jobnotes.exception.JobNotesNotExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anudish Jinturkar
 * Service class containing bussiness logic to perform CRUD operation on job notes.
 */
@Service
public class JobNotesService implements IJobNotesService {
    Logger log = LoggerFactory.getLogger(JobNotesService.class);

    @Autowired
    IJobNotesPersistence jobNotesPersistence;

    @Override
    public List<JobNotes> getAllJobNotes(String userID) throws JobNotesNotExistsException, JobNotesException, JobNotesInvalidArgumentException {
        List<JobNotes> jobNotesList = new ArrayList<JobNotes>();
        jobNotesList = null;
        try {
            jobNotesList = jobNotesPersistence.searchAll(userID);
        }catch (Exception e){
            log.error("Error while getting the contacts with userID: {} in the database.", userID);
            throw new JobNotesException("Error while getting job contacts with UserID: " + userID+
                    " in the database.", e);
        }
        return jobNotesList;
    }

    @Override
    public JobNotes saveJobNotes(JobNotes jobNotes) throws JobNotesNotExistsException, JobNotesException, JobNotesInvalidArgumentException {
        this.validateJobNotes(jobNotes);
        try {
           return this.jobNotesPersistence.save(jobNotes);
        }catch (Exception e){
            log.error("Error while saving the Note with NoteID: {} in the database.",jobNotes.getNoteID());
            throw new JobNotesException("Error while saving Notes with ID: "+ jobNotes.getNoteID()+" in the database.", e);
        }

    }

    @Override
    public JobNotes updateJobNotes(JobNotes jobNotes) throws JobNotesNotExistsException, JobNotesException, JobNotesInvalidArgumentException {
        this.validateJobNotes(jobNotes);
        try {
            return this.jobNotesPersistence.update(jobNotes);
        }catch (Exception e){
            log.error("Error while updating the Note with NoteID: {} in the database.",jobNotes.getNoteID());
            throw new JobNotesException("Error while updating Notes with ID: "+ jobNotes.getNoteID()+" in the database.", e);
        }

    }

    @Override
    public void deleteJobNotes(int jobNotesId) throws JobNotesNotExistsException, JobNotesException, JobNotesInvalidArgumentException {
        try {
            this.jobNotesPersistence.delete(jobNotesId);
        }catch (Exception e){
            log.error("Error while deleting the Note with NoteID: {} in the database.",jobNotesId);
            throw new JobNotesException("Error while deleting Notes with ID: "+ jobNotesId+" in the database.", e);
        }

    }
    private void validateJobNotes(JobNotes jobNotes) throws JobNotesInvalidArgumentException{
        if(null == jobNotes){
            log.warn("Invalid Job Notes argument.");
            throw new JobNotesInvalidArgumentException();
        }
    }
}
