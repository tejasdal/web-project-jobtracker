package com.web.project.jobtracker.blog;

import java.util.List;

/**
 * @author Zankrut Thakkar  B00856858
 * Persistence interface of the blog package
 */

public interface IBlogPersistence {
    List<Blog> getAll();

    List<Blog> getByUser(Long user_id);

    Blog save(Blog blog);

    Blog findById(Long blog_id);

    List<Blog> getByTitle(String title);

    void delete(long id);
}