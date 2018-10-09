package com.lucky.socialnetwork.controller.auth;

import com.lucky.socialnetwork.bean.User;
import com.lucky.socialnetwork.constant.Attributes;
import com.lucky.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController(value = "authUserController")
@RequestMapping("/auth/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public User getUserInfo(HttpServletRequest request) throws Exception{
        String uid = request.getAttribute(Attributes.UID).toString();
        return userService.getUserInfo(Integer.valueOf(uid));
    }

    @PatchMapping("/me")
    public void patchUserInfo(HttpServletRequest request, User user) throws Exception {
        String uid = request.getAttribute(Attributes.UID).toString();
        user.setId(Integer.valueOf(uid));
        System.out.println(user);
        userService.patchUserInfo(user);
    }

    @PostMapping("/follow")
    public void followUser(HttpServletRequest request, @RequestParam("uid") int uid) throws Exception {
        String followerId = request.getAttribute(Attributes.UID).toString();
        userService.follow(uid, Integer.valueOf(followerId));
    }

    @DeleteMapping("/follow")
    public void deleteFollow(HttpServletRequest request, @RequestParam("uid") int uid) throws Exception {
        String followerId = request.getAttribute(Attributes.UID).toString();
        userService.unfollow(uid, Integer.valueOf(followerId));
    }

}
