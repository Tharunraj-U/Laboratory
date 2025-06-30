package com.dev.Laboratory.security.jwt;

public class JwtAuthResponce {
    private String token;

    public String getToken() {
        return token;
    }

    public JwtAuthResponce setToken(String token) {
        this.token = token;
        return this;
    }

    public JwtAuthResponce(String token) {
        this.token = token;
    }
}
