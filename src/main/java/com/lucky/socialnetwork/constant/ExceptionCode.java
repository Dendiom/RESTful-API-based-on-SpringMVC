package com.lucky.socialnetwork.constant;

public enum ExceptionCode {

    JWT_ERROR(1000, "jwt error, please use valid token"),
    INTERNAL_ERROR(1001, "server internal error"),
    INVALID_PARAMETRE(1002, "invalid parameter"),

    USER_ALREADY_EXIST(1200, "user already exists"),
    USER_NOT_EXIST(1201, "user not exist"),
    USER_NOT_REGISTERED(1202, "user not registered"),
    PASSWORD_NOT_CORRECT(1203, "password not correct"),
    IMMUTABLE_FILED(1204, "immutable field"),
    ALREADY_FOllOWED(1205, "already followed"),
    NOT_FOllOWED(1206, "not followed"),
    ILLEGAL_OPERATION(1207, "illegal operation"),
    BLOG_NOT_EXIST(1208, "blog not exits"),
    WRONG_AUTHOR(1210, "wrong author");


    private final int value;
    private final String message;

    ExceptionCode(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
