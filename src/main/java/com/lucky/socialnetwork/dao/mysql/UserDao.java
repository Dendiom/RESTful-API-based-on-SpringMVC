package com.lucky.socialnetwork.dao.mysql;

import com.lucky.socialnetwork.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    int insertUser(@Param("username") String username, @Param("password") String password);

    User getUserById(@Param("id") int id);

    User getUserByUsername(@Param("username") String username);

    void updateUser(User user);

    void incrementCount(@Param("id") int id, @Param("field") String field);

    void decrementCount(@Param("id") int id, @Param("field") String field);

    void insertFollow(@Param("follow_id") int followId, @Param("follower_id") int followerId);

    int deleteFollow(@Param("follow_id") int followId, @Param("follower_id") int followerId);

    List<User> getFollows(@Param("id") int id, @Param("offset") int offset, @Param("count") int count);

    List<User> getFollowers(@Param("id") int id, @Param("offset") int offset, @Param("count") int count);

    List<User> getRankUsers(@Param("type") String type, @Param("offset") int offset, @Param("count") int count);
}
