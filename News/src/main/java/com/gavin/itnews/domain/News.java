package com.gavin.itnews.domain;

import java.util.Date;

public class News {

    private Integer id;

    private String title;

    private String link;

    private String image;

    private Integer likeCount;

    private Integer commentCount;

    private Date createdDate;

    private Integer userId;

    private Integer score;

    private Integer extData = 0;

    public Integer getExtData() {
        return extData;
    }

    public void setExtData(Integer extData) {
        this.extData = extData;
    }

    public News() {
    }

    public News(Integer id, String title, String link, String image, Integer likeCount, Integer commentCount, Date createdDate, Integer userId) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.image = image;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.createdDate = createdDate;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }


    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", image='" + image + '\'' +
                ", likeCount=" + likeCount +
                ", commentCount=" + commentCount +
                ", createdDate=" + createdDate +
                ", userId=" + userId +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}