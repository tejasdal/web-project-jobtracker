package com.web.project.jobtracker.jobactivities.exception;

/**
 * @author Anudish Jinturkar
 * Exception to define job activities does not exists in DB.
 */

public class JobActivitiesNotExistsException extends Exception {
    public JobActivitiesNotExistsException(){ }
    public JobActivitiesNotExistsException(int jobActivityId){
        super("Job Activities with ID: " + jobActivityId + " does not exists in the database.");
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
