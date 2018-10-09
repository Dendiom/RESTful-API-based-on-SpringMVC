package com.lucky.socialnetwork.controller.common;

import com.lucky.socialnetwork.bean.User;
import com.lucky.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "commonUserController")
@RequestMapping("/common/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public User getUser(@RequestParam(value = "uid") int id) throws Exception {
        return userService.getUserInfo(id);
    }

    @GetMapping("/{uid}/follow")
    public List<User> getFollows(@PathVariable(value = "uid") int id,
                                 @RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "count", required = false) Integer count) throws Exception {
        int pageDefault = page == null ? 1 : page;
        int countDefault  = count == null ? 20 : count;
        int offset = (pageDefault - 1) * countDefault;
        return userService.getFollows(id, offset, countDefault);
    }

    @GetMapping("/{uid}/follower")
    public List<User> getFollowers(@PathVariable(value = "uid") int id,
                                 @RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "count", required = false) Integer count) throws Exception {
        int pageDefault = page == null ? 1 : page;
        int countDefault  = count == null ? 20 : count;
        int offset = (pageDefault - 1) * countDefault;
        return userService.getFollowers(id, offset, countDefault);
    }
}
