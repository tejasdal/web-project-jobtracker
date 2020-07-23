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

@Service
public class JobContactsService implements IJobContactsService{
    Logger log = LoggerFactory.getLogger(JobContactsService.class);

    @Autowired
    private IJobContactsPersistence jobContactsPersistence;

    @Override
    public List<JobContacts> getAllJobContacts() {
        List<JobContacts> jobContacts = new ArrayList<JobContacts>();
        jobContacts = null;
        jobContacts = jobContactsPersistence.searchAll();
        return jobContacts;
    }

    @Override
    public JobContacts saveJobContacts(JobContacts jobContacts) throws JobContactsInvalidArgumentException, JobContactsNotExistsException, JobContactsException {
        this.validateJobContact(jobContacts);
       try {
           return this.jobContactsPersistence.save(jobContacts);
       }catch (Exception e){
           log.error("Error while updating the contact with ContactID: {} in the database.", jobContacts.getContactID());
           throw new JobContactsException("Error while updating job contacts with ID: " + jobContacts.getContactID()+
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
    public void deleteJobContacts(JobContacts jobContacts) throws JobContactsNotExistsException, JobContactsException {
        try {
            this.jobContactsPersistence.delete(jobContacts);
        }catch (Exception e){
            log.error("Error while updating the contact with ContactID: {} in the database.", jobContacts.getContactID());
            throw new JobContactsException("Error while updating job contacts with ID: " + jobContacts.getContactID()+
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
