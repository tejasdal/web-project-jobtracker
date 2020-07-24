package com.web.project.jobtracker.jobboard.exception;

/**
 * @author Tejas Patel
 * Exception to define any general error of job board.
 */
public class JobBoardException extends Exception {

    public JobBoardException() {
    }

    public JobBoardException(String message) {
        super(message);
    }

    public JobBoardException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobBoardException(Throwable cause) {
        super(cause);
    }

    public JobBoardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
