package com.dev.Laboratory.security.sign.dto;


import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;

    public String getPassword() {
        return password;
    }

    public RegisterRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public RegisterRequest setRole(Role role) {
        this.role = role;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public RegisterRequest setUsername(String username) {
        this.username = username;
        return this;
    }
}
