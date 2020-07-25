package com.web.project.jobtracker.blog;

import java.util.List;

public interface IBlogPersistence {
    List<Blog> getAll();

    List<Blog> getByUser(Long user_id);

    Blog save(Blog blog);

    Blog findById(Long blog_id);

    List<Blog> getByTitle(String title);
}