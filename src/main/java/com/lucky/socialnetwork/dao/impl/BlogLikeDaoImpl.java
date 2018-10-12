package com.lucky.socialnetwork.dao.impl;

import com.lucky.socialnetwork.dao.redis.BlogLikeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class BlogLikeDaoImpl implements BlogLikeDao {

    private static final String PREFIX_BLOG_USER_KEY = "Set_Blog2User_";
    private static final String PREFIX_USER_LIKE_BLOG = "Set_User2Blog_";

    private static String generateBlogUserKey(int id) {
        return PREFIX_BLOG_USER_KEY + id;
    }

    private static String generateUserBlogKey(int uid) {
        return PREFIX_USER_LIKE_BLOG + uid;
    }

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public int addBlogLikeUid(int uid, int id) {
        return redisTemplate.opsForSet().add(generateBlogUserKey(id), String.valueOf(uid)).intValue();
    }

    @Override
    public int addUserLikeBid(int uid, int id) {
        return redisTemplate.opsForSet().add(generateUserBlogKey(uid), String.valueOf(id)).intValue();
    }

    @Override
    public int deleteBlogLikeUid(int uid, int id) {
        return redisTemplate.opsForSet().remove(generateBlogUserKey(id), String.valueOf(uid)).intValue();
    }

    @Override
    public int deleteUserLikeBid(int uid, int id) {
        return redisTemplate.opsForSet().remove(generateUserBlogKey(uid), String.valueOf(id)).intValue();
    }

    @Override
    public void deleteAllBlogKeys(int id) {
        List<String> keys = new ArrayList<>();
        keys.add(generateBlogUserKey(id));
        redisTemplate.delete(keys);
    }

    @Override
    public boolean isUserLikeBlog(int uid, int id) {
        return redisTemplate.opsForSet().isMember(generateUserBlogKey(uid), String.valueOf(id));
    }

    @Override
    public boolean isBlogLikedByUser(int uid, int id) {
        return redisTemplate.opsForSet().isMember(generateBlogUserKey(id), String.valueOf(uid));
    }

    @Override
    public Set<String> getBlogLikeUid(int id) {
        return redisTemplate.opsForSet().members(generateBlogUserKey(id));
    }

    @Override
    public Set<String> getUserLikeBid(int uid) {
        return redisTemplate.opsForSet().members(generateUserBlogKey(uid));
    }
}
