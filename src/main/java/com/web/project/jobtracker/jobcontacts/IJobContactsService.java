package com.web.project.jobtracker.jobcontacts;

import com.web.project.jobtracker.jobcontacts.exception.JobContactsException;
import com.web.project.jobtracker.jobcontacts.exception.JobContactsInvalidArgumentException;
import com.web.project.jobtracker.jobcontacts.exception.JobContactsNotExistsException;

import java.util.List;

public interface IJobContactsService {
    List<JobContacts> getAllJobContacts(String userID) throws JobContactsInvalidArgumentException, JobContactsNotExistsException, JobContactsException;

    JobContacts saveJobContacts(JobContacts jobContacts) throws JobContactsInvalidArgumentException, JobContactsNotExistsException, JobContactsException;

    JobContacts updateJobContacts(JobContacts jobContacts) throws JobContactsInvalidArgumentException, JobContactsNotExistsException, JobContactsException;

    void deleteJobContacts(Long jobContactId) throws JobContactsNotExistsException, JobContactsException;

}
