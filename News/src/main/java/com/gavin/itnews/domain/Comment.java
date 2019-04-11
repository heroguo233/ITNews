package com.gavin.itnews.domain;

import java.util.Date;

public class Comment {
    private Integer id;

    private Integer userId;

    private Integer newsId;

    private Integer newsType;

    private Date createdDate;

    private Integer status;

    private String content;

    public Comment() {
    }

    public Comment(Integer id, Integer userId, Integer newsId, Integer newsType, Date createdDate, Integer status, String content) {
        this.id = id;
        this.userId = userId;
        this.newsId = newsId;
        this.newsType = newsType;
        this.createdDate = createdDate;
        this.status = status;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getNewsType() {
        return newsType;
    }

    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", newsId=" + newsId +
                ", newsType=" + newsType +
                ", createdDate=" + createdDate +
                ", status=" + status +
                ", content='" + content + '\'' +
                '}';
    }
}