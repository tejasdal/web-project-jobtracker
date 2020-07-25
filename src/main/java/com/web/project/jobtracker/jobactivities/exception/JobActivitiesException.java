package com.web.project.jobtracker.jobactivities.exception;

/**
 * @author Anudish Jinturkar
 * Exception to define any general error of job activities.
 */

public class JobActivitiesException extends Exception {
    public JobActivitiesException() {
        super("Please provide valid Job Activities data.");
    }

    public JobActivitiesException(String message) {
        super(message);
    }

    public JobActivitiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobActivitiesException(Throwable cause) {
        super(cause);
    }

    public JobActivitiesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
