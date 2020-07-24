package com.web.project.jobtracker.jobnotes.exception;

public class JobNotesException extends Exception{
    public JobNotesException(){
    }
    public JobNotesException(String message) {
        super(message);
    }

    public JobNotesException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobNotesException(Throwable cause) {
        super(cause);
    }

    public JobNotesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
