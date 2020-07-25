package com.web.project.jobtracker.jobcontacts.exception;

/**
 * @author Anudish Jinturkar
 * Exception to define job contacts does not exists in DB.
 */
public class JobContactsNotExistsException extends Exception {
    public  JobContactsNotExistsException(){ }
    public JobContactsNotExistsException(Long jobContactsId){
        super("Job Contacts with ID: " + jobContactsId + " does not exists in the database.");
    }

    public JobContactsNotExistsException(String message) {
        super(message);
    }

    public JobContactsNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobContactsNotExistsException(Throwable cause) {
        super(cause);
    }

    public JobContactsNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
