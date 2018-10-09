package com.lucky.socialnetwork.dao.impl;

import com.lucky.socialnetwork.dao.redis.BlogLikeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BlogLikeDaoImpl implements BlogLikeDao {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void addBlogLike() {
        redisTemplate.opsForSet().add("test", "5");
    }
}
