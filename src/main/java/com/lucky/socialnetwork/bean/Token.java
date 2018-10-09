package com.lucky.socialnetwork.bean;

public class Token {

    private String token;
    private int expire;


    public Token(String token, int expire) {
        this.token = token;
        this.expire = expire;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }
}
