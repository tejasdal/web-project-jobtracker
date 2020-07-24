package com.web.project.jobtracker.jobapplication.exception;

/**
 * @author Tejas Patel
 * Exception to define job application does not exists in DB.
 */
public class JobApplicationNotExistsException extends Exception{

    public JobApplicationNotExistsException() {
    }

    public JobApplicationNotExistsException(Long jobApplicationId){
        super("Job Application with ID: " + jobApplicationId + " does not exists in the database.");
    }

    public JobApplicationNotExistsException(String message) {
        super(message);
    }

    public JobApplicationNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobApplicationNotExistsException(Throwable cause) {
        super(cause);
    }

    public JobApplicationNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
