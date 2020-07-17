package com.web.project.jobtracker.jobapplication.exception;

public class JobApplicationException extends Exception {

    public JobApplicationException() {
    }

    public JobApplicationException(String message) {
        super(message);
    }

    public JobApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobApplicationException(Throwable cause) {
        super(cause);
    }

    public JobApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
