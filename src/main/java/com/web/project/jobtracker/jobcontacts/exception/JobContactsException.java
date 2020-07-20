package com.web.project.jobtracker.jobcontacts.exception;

public class JobContactsException extends Exception{
    public JobContactsException(){
    }
    public JobContactsException(String message) {
        super(message);
    }

    public JobContactsException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobContactsException(Throwable cause) {
        super(cause);
    }

    public JobContactsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
