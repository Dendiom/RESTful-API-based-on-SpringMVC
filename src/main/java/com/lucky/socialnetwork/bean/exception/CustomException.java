package com.lucky.socialnetwork.bean.exception;

import com.lucky.socialnetwork.constant.ExceptionCode;

public class CustomException extends Exception{

    private int code;
    private String message;

    public CustomException() {
        super();
    }

    public CustomException(ExceptionCode e) {
        super(e.getMessage());
        this.code = e.getValue();
        this.message = e.getMessage();
    }

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CustomException{" +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
