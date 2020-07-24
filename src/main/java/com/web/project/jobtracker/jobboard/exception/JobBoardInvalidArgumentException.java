package com.web.project.jobtracker.jobboard.exception;

/**
 * @author Tejas Patel
 * Exception to define invalid arguments to perform operation on job board.
 */
public class JobBoardInvalidArgumentException extends Exception {

    public JobBoardInvalidArgumentException() {
    }

    public JobBoardInvalidArgumentException(String message) {
        super(message);
    }

    public JobBoardInvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobBoardInvalidArgumentException(Throwable cause) {
        super(cause);
    }

    public JobBoardInvalidArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
