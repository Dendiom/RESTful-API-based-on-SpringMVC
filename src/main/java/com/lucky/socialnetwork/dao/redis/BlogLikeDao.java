package com.lucky.socialnetwork.dao.redis;

import java.util.List;
import java.util.Set;

public interface BlogLikeDao {

    int addBlogLikeUid(int uid, int id);

    int addUserLikeBid(int uid, int id);

    int deleteBlogLikeUid(int uid, int id);

    int deleteUserLikeBid(int uid, int id);

    void deleteAllBlogKeys(int id);

    boolean isUserLikeBlog(int uid, int id);

    boolean isBlogLikedByUser(int uid, int id);

    Set<String> getBlogLikeUid(int id);

    Set<String> getUserLikeBid(int uid);
}
