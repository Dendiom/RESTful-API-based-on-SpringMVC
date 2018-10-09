package com.lucky.socialnetwork.service;

import com.lucky.socialnetwork.bean.User;

import java.util.List;

public interface UserService {
    User getUserInfo(int id) throws Exception;

    User getUserInfo(String username) throws Exception;

    void patchUserInfo(User user) throws Exception;

    void follow(int followId, int followerId) throws Exception;

    void unfollow(int followId, int followerId) throws Exception;

    List<User> getFollows(int id, int offset, int count) throws Exception;

    List<User> getFollowers(int id, int offset, int count) throws Exception;

    List<User> getRankUsers(String type, int offset, int count) throws Exception;
}
