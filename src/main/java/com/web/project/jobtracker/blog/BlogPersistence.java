package com.web.project.jobtracker.blog;

import com.web.project.jobtracker.configurations.DBConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zankrut Thakkar  B00856858
 * Persistence class of the blog package
 */

@Repository
public class BlogPersistence implements IBlogPersistence {

    Logger log = LoggerFactory.getLogger(BlogPersistence.class);
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    public static final String FIND_ALL = "SELECT id,user_id,title,sub_title,content,keyword,created_date,updated_date,img FROM blog";
    public static final String FIND_BY_USER = "SELECT id,user_id,title,sub_title,content,keyword,created_date,updated_date FROM blog where user_id=?";
    public static final String INSERT = "INSERT INTO blog(user_id,title,sub_title,content,keyword,created_date,updated_date,img) VALUE(?, ?, ?, ?, ?, ?,?,?)";
    public static final String FIND_BY_ID = "SELECT id,user_id,title,sub_title,content,keyword,created_date,updated_date,img FROM blog WHERE id = ?";
    public static final String FIND_BY_TITLE = "SELECT id,user_id,title,sub_title,content,keyword,created_date,updated_date FROM blog where title like CONCAT('%',?,'%')";
    public static final String DELETE = "DELETE FROM blog WHERE id = ?";

    @Override
    public List<Blog> getAll() {
        try {
            this.getConnection();
            this.getPreparedStatement(FIND_ALL);
            ResultSet resultSet = this.preparedStatement.executeQuery();
            List<Blog> blogpost = new ArrayList<Blog>();
            while (resultSet.next()) {
                Blog blog = new Blog();
                blog.setId(resultSet.getLong(1));
                blog.setUserId(resultSet.getString(2));
                blog.setTitle(resultSet.getString(3));
                blog.setSubTitle(resultSet.getString(4));
                blog.setContent(resultSet.getString(5));
                blog.setKeyword(resultSet.getString(6));
                blog.setCreatedAt(resultSet.getTimestamp(7));
                blog.setUpdatedAt(resultSet.getTimestamp(8));
                Blob blob = resultSet.getBlob(9);
                if(blob != null) {
                    blog.setImage(blob.getBytes(1, (int) blob.length()));
                }
                blogpost.add(blog);
            }
            return blogpost;
        } catch (SQLException e) {
            log.error("Error executing select query on Blogs table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public List<Blog> getByUser(Long user_id) {
        try {
            this.getConnection();
            this.getPreparedStatement(FIND_BY_USER);
            this.preparedStatement.setLong(1, user_id);
            ResultSet resultSet = this.preparedStatement.executeQuery();
            List<Blog> blogPostByUser = new ArrayList<>();
            while (resultSet.next()) {
                Blog blogByUser = new Blog();
                blogByUser.setId(resultSet.getLong(1));
                blogByUser.setUserId(resultSet.getString(2));
                blogByUser.setTitle(resultSet.getString(3));
                blogByUser.setSubTitle(resultSet.getString(4));
                blogByUser.setContent(resultSet.getString(5));
                blogByUser.setKeyword(resultSet.getString(6));
                blogByUser.setCreatedAt(resultSet.getTimestamp(7));
                blogByUser.setUpdatedAt(resultSet.getTimestamp(8));
                Blob blob = resultSet.getBlob(9);
                if(blob != null) {
                    blogByUser.setImage(blob.getBytes(1, (int) blob.length()));
                }
                blogPostByUser.add(blogByUser);
            }
            return blogPostByUser;
        } catch (SQLException e) {
            log.error("Error executing select query on Blog table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public Blog save(Blog blog) {
        try {
            this.getConnection();
            this.getPreparedStatement(INSERT);
            this.preparedStatement.setString(1, blog.getUserId());
            this.preparedStatement.setString(2, blog.getTitle());
            this.preparedStatement.setString(3, blog.getSubTitle());
            this.preparedStatement.setString(4, blog.getContent());
            this.preparedStatement.setString(5, blog.getKeyword());
            this.preparedStatement.setTimestamp(6, blog.getCreatedAt());
            this.preparedStatement.setTimestamp(7, blog.getUpdatedAt());
            this.preparedStatement.setBlob(8, new SerialBlob( blog.getBlogImage().getBytes()));
            int result = this.preparedStatement.executeUpdate();
            if (result == 1) {
                this.statement = this.connection.createStatement();
                ResultSet rs = this.statement.executeQuery("SELECT LAST_INSERT_ID()");
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    return findById(id);
                }
            }
            return null;
        } catch (SQLException | IOException e) {
            log.error("Error executing insert query on Blog table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public Blog findById(Long blog_id) {
        try {
            this.getConnection();
            this.getPreparedStatement(FIND_BY_ID);
            this.preparedStatement.setLong(1, blog_id);
            ResultSet resultSet = this.preparedStatement.executeQuery();
            Blog blogById = null;
            if (resultSet.next()) {
                blogById = new Blog();
                blogById.setId(resultSet.getLong(1));
                blogById.setUserId(resultSet.getString(2));
                blogById.setTitle(resultSet.getString(3));
                blogById.setSubTitle(resultSet.getString(4));
                blogById.setContent(resultSet.getString(5));
                blogById.setKeyword(resultSet.getString(6));
                blogById.setCreatedAt(resultSet.getTimestamp(7));
                blogById.setUpdatedAt(resultSet.getTimestamp(8));
                Blob blob = resultSet.getBlob(9);
                if(blob != null) {
                    blogById.setImage(blob.getBytes(1, (int) blob.length()));
                }
            }
            return blogById;
        } catch (SQLException e) {
            log.error("Error executing select query on Blog table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public List<Blog> getByTitle(String title) {
        try {
            this.getConnection();
            this.getPreparedStatement(FIND_BY_TITLE);
            this.preparedStatement.setString(1, title);
            ResultSet resultSet = this.preparedStatement.executeQuery();
            List<Blog> blogPostByUser = new ArrayList<>();
            while (resultSet.next()) {
                Blog blogByTitle = new Blog();
                blogByTitle.setId(resultSet.getLong(1));
                blogByTitle.setUserId(resultSet.getString(2));
                blogByTitle.setTitle(resultSet.getString(3));
                blogByTitle.setSubTitle(resultSet.getString(4));
                blogByTitle.setContent(resultSet.getString(5));
                blogByTitle.setKeyword(resultSet.getString(6));
                blogByTitle.setCreatedAt(resultSet.getTimestamp(7));
                blogByTitle.setUpdatedAt(resultSet.getTimestamp(8));
                blogPostByUser.add(blogByTitle);
            }
            return blogPostByUser;
        } catch (SQLException e) {
            log.error("Error executing select query on Blog table: {}", e.getMessage());
            return null;
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }

    @Override
    public void delete(long id) {
        try {
            this.getConnection();
            this.getPreparedStatement(DELETE);
            this.preparedStatement.setLong(1, id);
            this.preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("Error executing delete query on Blog table: {}", e.getMessage());
        } finally {
            this.closePreparedStatement();
            this.cleanConnection();
        }
    }


    public void getConnection() throws SQLException {
        this.connection = DBConfig.instance().getDBConnection();
    }

    public void cleanConnection() {
        try {
            if (this.connection != null) {
                if (!this.connection.isClosed()) {
                    this.connection.close();
                }
            }
        } catch (SQLException e) {

        }
    }

    public void getPreparedStatement(String query) throws SQLException {
        this.preparedStatement = this.connection.prepareStatement(query);
    }

    public void closePreparedStatement() {
        try {
            if (null != this.preparedStatement) {
                this.preparedStatement.close();
            }
        } catch (SQLException e) {

        }

    }
}
