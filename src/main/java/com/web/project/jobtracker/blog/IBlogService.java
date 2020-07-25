package com.web.project.jobtracker.blog;

import com.web.project.jobtracker.blog.exception.BlogException;
import com.web.project.jobtracker.blog.exception.BlogInvalidArgumentException;
import com.web.project.jobtracker.blog.exception.BlogNotExistsException;
import java.util.List;


public interface IBlogService {
    List<Blog> getAllBlogs();
    List<Blog> getBlogByUser(Long user_id);
    Blog saveBlog(Blog blog) throws BlogException, BlogNotExistsException, BlogInvalidArgumentException;
    List<Blog> getBlogByTitle(String title);
    Blog getBlogById(Long id);
}
