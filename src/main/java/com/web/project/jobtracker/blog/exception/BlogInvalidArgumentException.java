package com.web.project.jobtracker.blog.exception;

public class BlogInvalidArgumentException extends Exception {
    public BlogInvalidArgumentException() {
        super("Please provide valid job application data.");
    }

    public BlogInvalidArgumentException(String message) {
        super(message);
    }

    public BlogInvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogInvalidArgumentException(Throwable cause) {
        super(cause);
    }

    public BlogInvalidArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
