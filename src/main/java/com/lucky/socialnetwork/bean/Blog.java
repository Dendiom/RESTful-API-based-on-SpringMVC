package com.lucky.socialnetwork.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;

public class Blog {

    @JsonIgnore
    private Integer id;
    @JsonIgnore
    private Integer uid;
    private String author; // from user.nickname
    private String title;
    private String text;
    private Integer likeCount;
    private Boolean isLike;  // from redis
    private Timestamp createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", uid=" + uid +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", likeCount=" + likeCount +
                ", isLike=" + isLike +
                ", createTime=" + createTime +
                '}';
    }
}
