package com.lucky.socialnetwork.interceptor;

import com.lucky.socialnetwork.bean.exception.CustomException;
import com.lucky.socialnetwork.constant.Attributes;
import com.lucky.socialnetwork.constant.ExceptionCode;
import com.lucky.socialnetwork.util.JWTUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {

        String token = httpServletRequest.getHeader(Attributes.TOKEN);
        if (token == null) {
            throw new CustomException(ExceptionCode.JWT_ERROR);
        }

        String uid = JWTUtil.verify(token);
        httpServletRequest.setAttribute(Attributes.UID, uid);
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}
