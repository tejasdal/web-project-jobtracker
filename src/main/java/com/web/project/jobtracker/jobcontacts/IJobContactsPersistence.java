package com.web.project.jobtracker.jobcontacts;

import java.util.List;

public interface IJobContactsPersistence {
    JobContacts save(JobContacts jobContacts);

    JobContacts update(JobContacts jobContacts);

    void delete(JobContacts jobContacts);

    List<JobContacts> searchAll();

    JobContacts findById(Long jobContactsId);
}
