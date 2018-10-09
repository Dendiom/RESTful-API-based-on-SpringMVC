package com.lucky.socialnetwork.controller.common;

import com.lucky.socialnetwork.bean.Blog;
import com.lucky.socialnetwork.bean.User;
import com.lucky.socialnetwork.service.BlogService;
import com.lucky.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/common/rank")
public class RankController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/user")
    public List<User> getRankUsers(@RequestParam(value = "type") String type,
                                   @RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "count", required = false) Integer count) throws Exception{
        int pageDefault = page == null ? 1 : page;
        int countDefault = count == null ? 20 : count;
        int offset = (pageDefault - 1) * countDefault;
        return userService.getRankUsers(type, offset, countDefault);
    }

    @GetMapping("/blog")
    public List<Blog> getRankBlogs(@RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "count", required = false) Integer count,
                                   @RequestParam(value = "uid", required = false) Integer uid) throws Exception{
        int pageDefault = page == null ? 1 : page;
        int countDefault = count == null ? 20 : count;
        int offset = (pageDefault - 1) * countDefault;

        return blogService.getBlogsByLikeCount(uid, offset, countDefault);
    }
}
