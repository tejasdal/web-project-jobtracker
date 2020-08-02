package com.web.project.jobtracker.blog;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.sql.Timestamp;

/**
 * @author Zankrut Thakkar  B00856858
 * Model class for storing Blog data
 */

public class Blog {

    private Long id;
    private String userId;
    private String title;
    private String subTitle;
    private String content;
    private String keyword;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private MultipartFile blogImage;
    private String type;
    private String fileName;
    private byte[] image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public MultipartFile getBlogImage() {
        return blogImage;
    }

    public void setBlogImage(MultipartFile blogImage) {
        this.blogImage = blogImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
