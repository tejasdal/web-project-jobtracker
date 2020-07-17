package com.web.project.jobtracker.jobapplication.exception;

public class JobApplicationInvalidArgumentException extends Exception {
    public JobApplicationInvalidArgumentException() {
        super("Please provide valid job application data.");
    }

    public JobApplicationInvalidArgumentException(String message) {
        super(message);
    }

    public JobApplicationInvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobApplicationInvalidArgumentException(Throwable cause) {
        super(cause);
    }

    public JobApplicationInvalidArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
