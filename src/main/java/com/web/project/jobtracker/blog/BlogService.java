package com.web.project.jobtracker.blog;

import com.web.project.jobtracker.blog.exception.BlogException;
import com.web.project.jobtracker.blog.exception.BlogInvalidArgumentException;
import com.web.project.jobtracker.blog.exception.BlogNotExistsException;
import com.web.project.jobtracker.jobapplication.JobApplication;
import com.web.project.jobtracker.jobapplication.exception.JobApplicationException;
import com.web.project.jobtracker.jobapplication.exception.JobApplicationInvalidArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService implements IBlogService {

    Logger log = LoggerFactory.getLogger(BlogService.class);
    @Autowired
    private IBlogPersistence blogPersistence;

    @Override
    public List<Blog> getAllBlogs() {
        List<Blog> blogs = new ArrayList<Blog>();
        blogs = blogPersistence.getAll();
        return blogs;
    }

    @Override
    public List<Blog> getBlogByUser(Long user_id) {
        List<Blog> userblog = new ArrayList<>();
        userblog = blogPersistence.getByUser(user_id);
        return userblog;
    }

    @Override
    public Blog saveBlog(Blog blog) throws BlogException, BlogInvalidArgumentException {
        this.validateBlog(blog);
        try {
            blog.setCreatedAt(new Timestamp(new Date().getTime()));
            blog.setUpdatedAt(new Timestamp(new Date().getTime()));
            return this.blogPersistence.save(blog);
        } catch (Exception e) {
            log.error("Error while saving new job application with ID: {} in the database.", blog.getId());
            throw new BlogException("Error while saving new job application with ID: " + blog.getId() +
                    " in the database.", e);
        }
    }

    @Override
    public List<Blog> getBlogByTitle(String title) {
        List<Blog> blogByTitle = new ArrayList<>();
        blogByTitle = blogPersistence.getByTitle(title);
        return blogByTitle;
    }

    @Override
    public Blog getBlogById(Long id) {
        Blog blogById = new Blog();
        blogById = blogPersistence.findById(id);
        return blogById;
    }

    private void validateBlog(Blog blog) throws BlogInvalidArgumentException {

        if (null == blog) {
            log.warn("Invalid blog argument.");
            throw new BlogInvalidArgumentException();
        }
    }


}
