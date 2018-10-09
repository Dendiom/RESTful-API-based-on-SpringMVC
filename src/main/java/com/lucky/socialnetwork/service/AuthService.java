package com.lucky.socialnetwork.service;

import com.lucky.socialnetwork.bean.User;

public interface AuthService {

    void register(String username, String password) throws Exception;
    int login(String username, String password) throws Exception;
}
