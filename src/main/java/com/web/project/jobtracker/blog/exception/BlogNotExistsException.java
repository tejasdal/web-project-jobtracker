package com.web.project.jobtracker.blog.exception;

public class BlogNotExistsException extends Exception{
    public BlogNotExistsException() {
    }

    public BlogNotExistsException(Long jobApplicationId){
        super("Job Application with ID: " + jobApplicationId + " does not exists in the database.");
    }

    public BlogNotExistsException(String message) {
        super(message);
    }

    public BlogNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogNotExistsException(Throwable cause) {
        super(cause);
    }

    public BlogNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
