package com.web.project.jobtracker.jobactivities.exception;

/**
 * @author Anudish Jinturkar
 * Exception to define invalid arguments to perform operation on job activities.
 */

public class JobActivitiesInvalidArgumentException extends Exception {
    public JobActivitiesInvalidArgumentException() {
        super("Please provide valid Job Activities data.");
    }

    public JobActivitiesInvalidArgumentException(String message) {
        super(message);
    }

    public JobActivitiesInvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobActivitiesInvalidArgumentException(Throwable cause) {
        super(cause);
    }

    public JobActivitiesInvalidArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
