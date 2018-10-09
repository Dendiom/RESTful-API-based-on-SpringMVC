package com.lucky.socialnetwork.service.impl;

import com.lucky.socialnetwork.bean.User;
import com.lucky.socialnetwork.bean.exception.CustomException;
import com.lucky.socialnetwork.constant.ExceptionCode;
import com.lucky.socialnetwork.dao.mysql.UserDao;
import com.lucky.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    DataSourceTransactionManager txManager;

    public User getUserInfo(int id) throws Exception {
        User user = userDao.getUserById(id);
        if (user == null) {
            throw new CustomException(ExceptionCode.USER_NOT_EXIST);
        }

        return user;
    }

    public User getUserInfo(String username) throws Exception {
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            throw new CustomException(ExceptionCode.USER_NOT_EXIST);
        }

        return user;
    }

    public void patchUserInfo(User user) throws Exception {
        if (user.getUsername() != null) {
            throw new CustomException(ExceptionCode.IMMUTABLE_FILED);
        }

        userDao.updateUser(user);
    }

    // programmatic transaction
    public void follow(int followId, int followerId) throws Exception {
        if (followId == followerId) {
            throw new CustomException(ExceptionCode.ILLEGAL_OPERATION);
        }

        User follow = userDao.getUserById(followId);
        if (follow == null) {
            throw new CustomException(ExceptionCode.USER_NOT_EXIST);
        }


        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = txManager.getTransaction(def);
        try {
            userDao.insertFollow(followId, followerId);
            userDao.incrementCount(followId, "followerCount");
            userDao.incrementCount(followerId, "followCount");
        }
        catch (Exception ex) {
            txManager.rollback(status);
            if (ex instanceof DuplicateKeyException) {
                throw new CustomException(ExceptionCode.ALREADY_FOllOWED);
            }

            throw ex;
        }
        txManager.commit(status);
    }

    public void unfollow(int followId, int followerId) throws Exception {
        if (followId == followerId) {
            throw new CustomException(ExceptionCode.ILLEGAL_OPERATION);
        }

        User follow = userDao.getUserById(followId);
        if (follow == null) {
            throw new CustomException(ExceptionCode.USER_NOT_EXIST);
        }

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = txManager.getTransaction(def);
        try {
            int deleteCount = userDao.deleteFollow(followId, followerId);
            if (deleteCount == 0) {
                throw new CustomException(ExceptionCode.NOT_FOllOWED);
            }

            userDao.decrementCount(followId, "followerCount");
            userDao.decrementCount(followerId, "followCount");
        }
        catch (Exception ex) {
            txManager.rollback(status);
            throw ex;
        }
        txManager.commit(status);
    }

    public List<User> getFollows(int id, int offset, int count) throws Exception{
        if (offset < 0 || count < 0) {
            throw new CustomException(ExceptionCode.INVALID_PARAMETRE);
        }

        return userDao.getFollows(id, offset, count);
    }

    public List<User> getFollowers(int id, int offset, int count) throws Exception{
        if (offset < 0 || count < 0) {
            throw new CustomException(ExceptionCode.INVALID_PARAMETRE);
        }

        return userDao.getFollowers(id, offset, count);
    }

    public List<User> getRankUsers(String type, int offset, int count) throws Exception {
        if (offset < 0 || count < 0) {
            throw new CustomException(ExceptionCode.INVALID_PARAMETRE);
        }

        return userDao.getRankUsers(type, offset, count);
    }
}
