package com.web.project.jobtracker.jobnotes;

import com.web.project.jobtracker.jobnotes.exception.JobNotesException;
import com.web.project.jobtracker.jobnotes.exception.JobNotesInvalidArgumentException;
import com.web.project.jobtracker.jobnotes.exception.JobNotesNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Anudish Jinturkar
 * Controller to expose REST APIs to perform CRUD operation on job notes.
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/notes")
public class JobNotesController {

    @Autowired
    private IJobNotesService jobNotesService;

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JobNotes> getAllNotes(@RequestParam("userID") String userID)throws JobNotesNotExistsException, JobNotesException, JobNotesInvalidArgumentException {
        return  this.jobNotesService.getAllJobNotes(userID); }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobNotes saveJobNotes(@RequestBody JobNotes jobNotes) throws JobNotesNotExistsException, JobNotesInvalidArgumentException, JobNotesException {
        return this.jobNotesService.saveJobNotes(jobNotes);}

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobNotes updateJobNotes(@RequestBody JobNotes jobNotes) throws JobNotesNotExistsException, JobNotesException, JobNotesInvalidArgumentException{
        return this.jobNotesService.updateJobNotes(jobNotes); }

    @DeleteMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteJobNotes(@RequestParam("noteID") int jobNotesId) throws JobNotesNotExistsException, JobNotesException, JobNotesInvalidArgumentException{
        this.jobNotesService.deleteJobNotes(jobNotesId); }
}
