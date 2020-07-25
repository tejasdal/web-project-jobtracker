package com.web.project.jobtracker.blog;

import com.web.project.jobtracker.blog.exception.BlogException;
import com.web.project.jobtracker.blog.exception.BlogInvalidArgumentException;
import com.web.project.jobtracker.blog.exception.BlogNotExistsException;

import java.util.List;

/**
 * @author Zankrut Thakkar  B00856858
 * Service interface of the blog package
 */


public interface IBlogService {
    List<Blog> getAllBlogs();

    List<Blog> getBlogByUser(Long user_id);

    Blog saveBlog(Blog blog) throws BlogException, BlogNotExistsException, BlogInvalidArgumentException;

    List<Blog> getBlogByTitle(String title);

    Blog getBlogById(Long id);

    void deleteBlogById(Long id) throws BlogException, BlogNotExistsException, BlogInvalidArgumentException;
}
