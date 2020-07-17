package com.web.project.jobtracker.jobboard.exception;

public class JobBoardNotExistsException extends Exception {

    public JobBoardNotExistsException() {
        super();
    }

    public JobBoardNotExistsException(String message) {
        super(message);
    }

    public JobBoardNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobBoardNotExistsException(Throwable cause) {
        super(cause);
    }

    protected JobBoardNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
