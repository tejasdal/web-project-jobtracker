package com.web.project.jobtracker.blog;

import com.web.project.jobtracker.blog.exception.BlogException;
import com.web.project.jobtracker.blog.exception.BlogInvalidArgumentException;
import com.web.project.jobtracker.blog.exception.BlogNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zankrut Thakkar  B00856858
 * Controller class of the blog package
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Blog> getBlogs() {
        return blogService.getAllBlogs();
    }

    @GetMapping(value = "/myblogs",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Blog> getBlogByUser(@RequestParam("user_id") Long userId) throws BlogNotExistsException {
        return this.blogService.getBlogByUser(userId);
    }

    @GetMapping(value = "/blogid",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Blog getBlogById(@RequestParam("id") Long userId) throws BlogNotExistsException {
        return this.blogService.getBlogById(userId);
    }

    @GetMapping(value = "/blogsbytitle",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Blog> getBlogByTitle(@RequestParam("title") String title) throws BlogNotExistsException {
        return this.blogService.getBlogByTitle(title);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Blog insertBlog(@RequestBody Blog blog) throws BlogException, BlogInvalidArgumentException,BlogNotExistsException {
        return this.blogService.saveBlog(blog);
    }
    @DeleteMapping(value = "/blogid", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteBlog(@RequestParam("id") Long blogId) throws BlogNotExistsException, BlogInvalidArgumentException, BlogException {
        this.blogService.deleteBlogById(blogId);
    }


}
