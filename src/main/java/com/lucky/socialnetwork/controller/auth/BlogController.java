package com.lucky.socialnetwork.controller.auth;

import com.lucky.socialnetwork.bean.Blog;
import com.lucky.socialnetwork.constant.Attributes;
import com.lucky.socialnetwork.service.BlogService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController(value = "authBlogController")
@RequestMapping("/auth/user/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("")
    public void submitBlog(HttpServletRequest request, @RequestParam("title") String title,
                           @RequestParam("text") String text) throws Exception{
        int uid = Integer.valueOf(request.getAttribute(Attributes.UID).toString());
        Blog blog = new Blog();
        blog.setUid(uid);
        blog.setTitle(title);
        blog.setText(text);
        blogService.submitBlog(blog);
    }

    @PatchMapping("")
    public void updateBlog(HttpServletRequest request, @RequestParam("id") int  id,
                           @RequestParam("text") String text) throws Exception {
        int uid = Integer.valueOf(request.getAttribute(Attributes.UID).toString());
        blogService.updateBlog(uid, id, text);
    }

    @GetMapping("")
    public List<Blog> getUserBlogs(HttpServletRequest request,
                                   @RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "count", required = false) Integer count) throws Exception{
        int uid = Integer.valueOf(request.getAttribute(Attributes.UID).toString());
        int pageDefault = page == null ? 1 : page;
        int countDefault  = count == null ? 20 : count;
        int offset = (pageDefault - 1) * countDefault;
        return blogService.getUserBlogs(uid, offset, countDefault);
    }

    @DeleteMapping("")
    public void deleteBlog(HttpServletRequest request, @RequestParam("id") int  id) throws Exception {
        int uid = Integer.valueOf(request.getAttribute(Attributes.UID).toString());
        blogService.deleteBlog(uid, id);
    }

    @PostMapping("/like")
    public void likeBlog(HttpServletRequest request, @RequestParam("id") int id,
                         @RequestParam("like") int like) throws Exception {
        int uid = Integer.valueOf(request.getAttribute(Attributes.UID).toString());
        blogService.likeBlog(uid, id, 1);

    }

}
