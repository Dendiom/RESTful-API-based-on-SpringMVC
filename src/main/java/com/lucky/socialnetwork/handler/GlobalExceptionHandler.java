package com.lucky.socialnetwork.handler;

import com.lucky.socialnetwork.bean.exception.CustomException;
import com.lucky.socialnetwork.bean.exception.ExceptionInfo;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionInfo requestHandlingNoHandlerFound(HttpServletRequest req, NoHandlerFoundException e) {
        return new ExceptionInfo(HttpStatus.NOT_FOUND.value(), "page not found", req.getRequestURL().toString());
    }

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionInfo handleCustomException(HttpServletRequest req, Exception e) {
        return new ExceptionInfo((CustomException) e, req.getRequestURL().toString());
    }

    @ExceptionHandler()
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionInfo handleInternalException(HttpServletRequest req, Exception e) {
        return new ExceptionInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), req.getRequestURL().toString());
    }

}
