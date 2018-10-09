package com.lucky.socialnetwork.controller.common;

import com.lucky.socialnetwork.bean.Token;
import com.lucky.socialnetwork.bean.User;
import com.lucky.socialnetwork.bean.exception.CustomException;
import com.lucky.socialnetwork.constant.ExceptionCode;
import com.lucky.socialnetwork.service.AuthService;
import com.lucky.socialnetwork.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void register(@RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password) throws Exception {
        authService.register(username, password);
    }

    @PostMapping("/login")
    public Token login(@RequestParam(value = "username") String username,
                       @RequestParam(value = "password") String password) throws Exception {

        int id = authService.login(username, password);
        String token = JWTUtil.createToken(String.valueOf(id));
        return new Token(token, JWTUtil.EXPIRE / 1000);
    }
}

