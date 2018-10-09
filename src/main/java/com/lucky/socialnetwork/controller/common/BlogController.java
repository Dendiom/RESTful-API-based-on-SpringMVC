package com.lucky.socialnetwork.controller.common;

import com.lucky.socialnetwork.bean.Blog;
import com.lucky.socialnetwork.bean.User;
import com.lucky.socialnetwork.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "commonBlogController")
@RequestMapping("/common/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("")
    public Blog getUser(@RequestParam(value = "id") int id,
                        @RequestParam(value = "uid", required = false) Integer uid) throws Exception {
        return blogService.getBlogById(id, uid) ;
    }
}
