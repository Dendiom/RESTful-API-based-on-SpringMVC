package com.lucky.socialnetwork.service.impl;

import com.lucky.socialnetwork.bean.Blog;
import com.lucky.socialnetwork.bean.exception.CustomException;
import com.lucky.socialnetwork.constant.ExceptionCode;
import com.lucky.socialnetwork.dao.mysql.BlogDao;
import com.lucky.socialnetwork.dao.redis.BlogLikeDao;
import com.lucky.socialnetwork.dao.mysql.UserDao;
import com.lucky.socialnetwork.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BlogLikeDao blogLikeDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void submitBlog(Blog blog) {
        blogDao.insertBlog(blog);
        userDao.incrementCount(blog.getUid(), "blogCount");
    }


    //todo
    public Blog getBlogById(int id, Integer uid) throws Exception {
        Blog blog = blogDao.getBlogById(id);
        if (blog == null) {
            throw new CustomException(ExceptionCode.BLOG_NOT_EXIST);
        }

        if (uid == null) {
            blog.setLike(false);
        } else {

        }
        return blog;
    }

    public void updateBlog(int uid, int id, String text) throws Exception {
        Blog blog = blogDao.getBlogById(id);
        if (blog == null) {
            throw new CustomException(ExceptionCode.BLOG_NOT_EXIST);
        }

        if (blog.getUid() != uid) {
            throw new CustomException(ExceptionCode.WRONG_AUTHOR);
        }

        blogDao.updateBlog(id, text);
    }

    //todo
    public List<Blog> getUserBlogs(int uid, int offset, int count) throws Exception {
        if (offset < 0 || count < 0) {
            throw new CustomException(ExceptionCode.INVALID_PARAMETRE);
        }

        List<Blog> blogs = blogDao.getUserBlogs(uid, count, offset);
        return blogs;
    }

    //todo
    public List<Blog> getBlogsByLikeCount(Integer uid, int offset, int count) throws Exception {
        if (offset < 0 || count < 0) {
            throw new CustomException(ExceptionCode.INVALID_PARAMETRE);
        }

        List<Blog> blogs = blogDao.getBlogsByLikeCount(count, offset);
        if (uid == null) {
            for (Blog blog : blogs) {
                blog.setLike(false);
            }
        }
        return blogs;
    }

    //todo
    public List<Blog> getFollowedBlogs(int uid, int offset, int count) throws Exception {
        if (offset < 0 || count < 0) {
            throw new CustomException(ExceptionCode.INVALID_PARAMETRE);
        }

        return null;
    }

    //todo
    public void deleteBlog(int uid, int id) throws Exception {

    }

    //todo
    public void likeBlog(int uid, int id, int like) throws Exception {
        blogLikeDao.addBlogLike();
    }
}
