package com.web.project.jobtracker.jobnotes.exception;

/**
 * @author Anudish Jinturkar
 * Exception to define job notes does not exists in DB.
 */

public class JobNotesNotExistsException extends Exception {
    public JobNotesNotExistsException(){ }
    public JobNotesNotExistsException(int jobNoteID){
        super("Job Notes with ID: " + jobNoteID + " does not exists in the database.");
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
