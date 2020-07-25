package com.web.project.jobtracker.jobcontacts;

import com.web.project.jobtracker.jobcontacts.exception.JobContactsException;
import com.web.project.jobtracker.jobcontacts.exception.JobContactsInvalidArgumentException;
import com.web.project.jobtracker.jobcontacts.exception.JobContactsNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Anudish Jinturkar
 * Controller to expose REST APIs to perform CRUD operation on job contacts.
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/contact")
public class JobContactsController {

    @Autowired
    private IJobContactsService jobContactsService;

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JobContacts> getAllJobContacts(@RequestParam("userID") String userID) throws JobContactsException, JobContactsInvalidArgumentException, JobContactsNotExistsException {
        return this.jobContactsService.getAllJobContacts(userID);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobContacts insertContact(@RequestBody JobContacts jobContacts) throws JobContactsException, JobContactsInvalidArgumentException, JobContactsNotExistsException {
        return this.jobContactsService.saveJobContacts(jobContacts);
    }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobContacts updateContact(@RequestBody JobContacts jobContacts) throws JobContactsNotExistsException, JobContactsInvalidArgumentException, JobContactsException{
        return this.jobContactsService.updateJobContacts(jobContacts);
    }

    @DeleteMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteContact(@RequestParam("contactID") Long jobContactId) throws JobContactsException, JobContactsInvalidArgumentException, JobContactsNotExistsException {
        this.jobContactsService.deleteJobContacts(jobContactId);
    }
}
