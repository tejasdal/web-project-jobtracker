package com.web.project.jobtracker.jobcontacts;

import com.web.project.jobtracker.jobcontacts.exception.JobContactsException;
import com.web.project.jobtracker.jobcontacts.exception.JobContactsInvalidArgumentException;
import com.web.project.jobtracker.jobcontacts.exception.JobContactsNotExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anudish Jinturkar
 * Service class containing bussiness logic to perform CRUD operation on job contacts.
 */
@Service
public class JobContactsService implements IJobContactsService{
    Logger log = LoggerFactory.getLogger(JobContactsService.class);

    @Autowired
    private IJobContactsPersistence jobContactsPersistence;

    @Override
    public List<JobContacts> getAllJobContacts(String userID) throws JobContactsInvalidArgumentException, JobContactsNotExistsException, JobContactsException {
        List<JobContacts> jobContacts = new ArrayList<JobContacts>();
        jobContacts = null;
        try {
            jobContacts = jobContactsPersistence.searchAll(userID);
        }catch (Exception e){
            log.error("Error while getting the contacts with userID: {} in the database.", userID);
            throw new JobContactsException("Error while getting job contacts with UserID: " + userID+
                    " in the database.", e);
        }
        return jobContacts;
    }

    @Override
    public JobContacts saveJobContacts(JobContacts jobContacts) throws JobContactsInvalidArgumentException, JobContactsNotExistsException, JobContactsException {
        this.validateJobContact(jobContacts);
       try {
           return this.jobContactsPersistence.save(jobContacts);
       }catch (Exception e){
           log.error("Error while saving the contact with ContactID: {} in the database.", jobContacts.getContactID());
           throw new JobContactsException("Error while saving job contacts with ID: " + jobContacts.getContactID()+
                   " in the database.", e);
       }
    }

    @Override
    public JobContacts updateJobContacts(JobContacts jobContacts) throws JobContactsInvalidArgumentException, JobContactsNotExistsException, JobContactsException {
        this.validateJobContact(jobContacts);
        try {
            return this.jobContactsPersistence.update(jobContacts);
        }catch (Exception e){
            log.error("Error while updating the contact with ContactID: {} in the database.", jobContacts.getContactID());
            throw new JobContactsException("Error while updating job contacts with ID: " + jobContacts.getContactID()+
                    " in the database.", e);
        }
    }

    @Override
    public void deleteJobContacts(Long jobContactId) throws JobContactsNotExistsException, JobContactsException {
        try {
            this.jobContactsPersistence.delete(jobContactId);
        }catch (Exception e){
            log.error("Error while deleting the contact with ContactID: {} in the database.", jobContactId);
            throw new JobContactsException("Error while deleting job contacts with ID: " + jobContactId+
                    " in the database.", e);
        }
    }

    private void validateJobContact(JobContacts jobContacts) throws JobContactsInvalidArgumentException{
        if( null == jobContacts){
            log.warn("Invalid Job Contacts argument.");
            throw new  JobContactsInvalidArgumentException();
        }
    }
}
