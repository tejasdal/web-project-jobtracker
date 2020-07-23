package com.web.project.jobtracker.jobnotes.exception;

public class JobNotesNotExistsException extends Exception {
    public JobNotesNotExistsException(){ }
    public JobNotesNotExistsException(Long jobContactsId){
        super("Job Contacts with ID: " + jobContactsId + " does not exists in the database.");
    }

    public JobNotesNotExistsException(String message) {
        super(message);
    }

    public JobNotesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobNotesNotExistsException(Throwable cause) {
        super(cause);
    }

    public JobNotesNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
