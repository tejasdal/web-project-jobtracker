package com.web.project.jobtracker.jobboard;

import com.web.project.jobtracker.jobboard.exception.JobBoardException;
import com.web.project.jobtracker.jobboard.exception.JobBoardInvalidArgumentException;
import com.web.project.jobtracker.jobboard.exception.JobBoardNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tejas Patel
 * Controller to expose REST APIs to perform CRUD operation on job board.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/job-board")
public class JobBoardController {

    @Autowired
    private IJobBoardService jobBoardService;

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobBoard getJobBoard(@RequestParam("id") Long jobBoardId) throws JobBoardNotExistsException {
        return this.jobBoardService.getJobBoard(jobBoardId);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobBoard saveJobBoard(@RequestBody JobBoard jobBoard) throws JobBoardException, JobBoardInvalidArgumentException {
        return this.jobBoardService.saveJobBoard(jobBoard);
    }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobBoard updateJobBoard(@RequestBody JobBoard jobBoard) throws JobBoardNotExistsException, JobBoardException, JobBoardInvalidArgumentException {
        return this.jobBoardService.updateJobBoard(jobBoard);
    }

    @DeleteMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteJobBoard(@RequestParam("id") Long jobBoardId) throws JobBoardException, JobBoardNotExistsException {
        this.jobBoardService.deleteJobBoard(jobBoardId);
    }

    @GetMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobBoard getJobBoardForUser(@RequestParam("userId") String userId) throws JobBoardException {
        return this.jobBoardService.getJobBoardForUser(userId);
    }
}
