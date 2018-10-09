package com.lucky.socialnetwork.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.sql.Timestamp;

public class User {

    @JsonIgnore
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private String nickname;
    private Integer sex;
    private Integer blogCount;
    private Integer followCount;
    private Integer followerCount;
    private String description;
    private Timestamp createTime;
    @JsonIgnore
    private Timestamp updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(int blogCount) {
        this.blogCount = blogCount;
    }

    public Integer getFollowCount() {
        return followCount;
    }

    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int follwerCount) {
        this.followerCount = follwerCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", blogCount=" + blogCount +
                ", followCount=" + followCount +
                ", followerCount=" + followerCount +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
