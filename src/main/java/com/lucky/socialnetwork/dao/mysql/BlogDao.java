package com.lucky.socialnetwork.dao.mysql;

import com.lucky.socialnetwork.bean.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogDao {
    int insertBlog(Blog blog);

    void updateBlog(@Param("id") int id, @Param("text") String text);

    Blog getBlogById(@Param("id") int id);

    List<Blog> getUserBlogs(@Param("id") int id, @Param("limit") int limit, @Param("offset") int offset);

    List<Blog> getBlogsByLikeCount(@Param("limit") int limit, @Param("offset") int offset);

    List<Blog> getFollowedBlogs(@Param("id") int id, @Param("limit") int limit, @Param("offset") int offset);

    int deleteBlog(@Param("id") int id);
}
