package com.lucky.socialnetwork.service;

import com.lucky.socialnetwork.bean.Blog;

import java.util.List;

public interface BlogService {
    void submitBlog(Blog blog);

    void updateBlog(int uid, int id, String text) throws Exception;

    Blog getBlogById(int id, Integer uid) throws Exception;

    List<Blog> getUserBlogs(int uid, int offset, int count) throws Exception;

    List<Blog> getBlogsByLikeCount(Integer uid, int offset, int count) throws Exception;

    List<Blog> getFollowedBlogs(int uid, int offset, int count) throws Exception;

    void deleteBlog(int uid, int id) throws Exception;

    void likeOrUnlikeBlog(int uid, int id, int option) throws Exception;
}
