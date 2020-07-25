package com.web.project.jobtracker.jobnotes.exception;

/**
 * @author Anudish Jinturkar
 * Exception to define invalid arguments to perform operation on job notes.
 */

public class JobNotesInvalidArgumentException extends Exception {
    public JobNotesInvalidArgumentException() {
        super("Please provide valid Job Notes data.");
    }

    public JobNotesInvalidArgumentException(String message) {
        super(message);
    }

    public JobNotesInvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobNotesInvalidArgumentException(Throwable cause) {
        super(cause);
    }

    public JobNotesInvalidArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
