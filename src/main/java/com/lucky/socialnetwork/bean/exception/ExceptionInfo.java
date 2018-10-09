package com.lucky.socialnetwork.bean.exception;

public class ExceptionInfo {

    private int code;
    private String message;
    private String url;

    public ExceptionInfo(int code, String message, String url) {
        this.code = code;
        this.message = message;
        this.url = url;
    }

    public ExceptionInfo(CustomException e, String url) {
        this.code = e.getCode();
        this.message = e.getMessage();
        this.url = url;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
