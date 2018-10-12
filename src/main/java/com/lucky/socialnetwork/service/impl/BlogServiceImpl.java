package com.lucky.socialnetwork.service.impl;

import com.lucky.socialnetwork.bean.Blog;
import com.lucky.socialnetwork.bean.exception.CustomException;
import com.lucky.socialnetwork.constant.ExceptionCode;
import com.lucky.socialnetwork.dao.mysql.BlogDao;
import com.lucky.socialnetwork.dao.redis.BlogLikeDao;
import com.lucky.socialnetwork.dao.mysql.UserDao;
import com.lucky.socialnetwork.service.BlogService;
import com.sun.tools.doclets.internal.toolkit.util.ClassUseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.spec.ECField;
import java.util.List;
import java.util.Set;

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

    @Transactional(propagation = Propagation.REQUIRED)
    public Blog getBlogById(int id, Integer uid) throws Exception {
        Blog blog = blogDao.getBlogById(id);
        if (blog == null) {
            throw new CustomException(ExceptionCode.BLOG_NOT_EXIST);
        }

        if (uid == null) {
            blog.setLike(false);
        } else {
            blog.setLike(blogLikeDao.isUserLikeBlog(uid, id));
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

    public List<Blog> getUserBlogs(int uid, int offset, int count) throws Exception {
        if (offset < 0 || count < 0) {
            throw new CustomException(ExceptionCode.INVALID_PARAMETRE);
        }

        List<Blog> blogs = blogDao.getUserBlogs(uid, count, offset);
        setBlogsLike(blogs, uid);
        return blogs;
    }

    public List<Blog> getBlogsByLikeCount(Integer uid, int offset, int count) throws Exception {
        if (offset < 0 || count < 0) {
            throw new CustomException(ExceptionCode.INVALID_PARAMETRE);
        }

        List<Blog> blogs = blogDao.getBlogsByLikeCount(count, offset);
        if (uid == null) {
            for (Blog blog : blogs) {
                blog.setLike(false);
            }
        } else {
            setBlogsLike(blogs, uid);
        }

        return blogs;
    }

    //todo
    public List<Blog> getFollowedBlogs(int uid, int offset, int count) throws Exception {
        if (offset < 0 || count < 0) {
            throw new CustomException(ExceptionCode.INVALID_PARAMETRE);
        }

        List<Blog> blogs = blogDao.getFollowedBlogs(uid, offset, count);
        setBlogsLike(blogs, uid);
        return blogs;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBlog(int uid, int id) throws Exception {
        Blog blog = blogDao.getBlogById(id);
        if (blog == null) {
            throw new CustomException(ExceptionCode.BLOG_NOT_EXIST);
        }

        if (blog.getUid() != uid) {
            throw new CustomException(ExceptionCode.USER_NOT_HAS_THIS_BLOG);
        }

        blogDao.deleteBlog(id);
        blogLikeDao.deleteAllBlogKeys(id);
        userDao.decrementCount(uid, "blogCount");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void likeOrUnlikeBlog(int uid, int id, int option) throws Exception {
//        Blog blog = blogDao.getBlogById(id);
//        if (blog == null) {
//            throw new CustomException(ExceptionCode.BLOG_NOT_EXIST);
//        }

        if (option == 0) { // like
            int count = blogLikeDao.addBlogLikeUid(uid, id);
            if (count == 0) {
                throw new CustomException(ExceptionCode.ALREADY_LIKED);
            }

            blogLikeDao.addUserLikeBid(uid, id);
            blogDao.incrementLikeCount(id);
        } else { // unlike
            int count = blogLikeDao.deleteBlogLikeUid(uid, id);
            if (count == 0) {
                throw new CustomException(ExceptionCode.NOT_LIKE);
            }

            blogLikeDao.deleteUserLikeBid(uid, id);
            blogDao.decrementLikeCount(id);
        }
    }

    private void setBlogsLike(List<Blog> blogs, int uid) {
        if (blogs == null) {
            return;
        }

        Set<String> likeBlogs = blogLikeDao.getUserLikeBid(uid);
        if (likeBlogs == null) {
            for (Blog blog : blogs) {
                blog.setLike(false);
            }
        } else {
            for (Blog blog : blogs) {
                blog.setLike(likeBlogs.contains(String.valueOf(blog.getId())));
            }
        }
    }
}
