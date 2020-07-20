package com.web.project.jobtracker.jobactivities.exception;

public class JobActivitiesNotExistsException extends Exception {
    public JobActivitiesNotExistsException(){ }
    public JobActivitiesNotExistsException(Long jobContactsId){
        super("Job Contacts with ID: " + jobContactsId + " does not exists in the database.");
    }

    public JobActivitiesNotExistsException(String message) {
        super(message);
    }

    public JobActivitiesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobActivitiesNotExistsException(Throwable cause) {
        super(cause);
    }

    public JobActivitiesNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
