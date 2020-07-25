package com.web.project.jobtracker.jobcontacts;

import java.util.List;

/**
 * @author Anudish Jinturkar
 * Persistence interface containing persistence methods signature that will perform CRUD operation on job contacts of DB.
 */

public interface IJobContactsPersistence {
    JobContacts save(JobContacts jobContacts);

    JobContacts update(JobContacts jobContacts);

    void delete(Long jobContactId);

    List<JobContacts> searchAll(String userID);

    JobContacts findById(Long jobContactsId);
}
