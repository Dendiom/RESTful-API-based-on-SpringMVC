package com.lucky.socialnetwork.service.impl;

import com.lucky.socialnetwork.bean.User;
import com.lucky.socialnetwork.bean.exception.CustomException;
import com.lucky.socialnetwork.constant.ExceptionCode;
import com.lucky.socialnetwork.dao.mysql.UserDao;
import com.lucky.socialnetwork.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDao userDao;

    public void register(String username, String password) throws Exception{
        User user = userDao.getUserByUsername(username);
        if (user != null) {
            throw new CustomException(ExceptionCode.USER_ALREADY_EXIST);
        }

        userDao.insertUser(username, password);
    }

    public int login(String username, String password) throws Exception {
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            throw new CustomException(ExceptionCode.USER_NOT_REGISTERED);
        }

        if (!user.getPassword().equals(password)) {
            throw new CustomException(ExceptionCode.PASSWORD_NOT_CORRECT);
        }

        return user.getId();
    }
}
