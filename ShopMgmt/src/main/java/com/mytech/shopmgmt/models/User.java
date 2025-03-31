package com.mytech.shopmgmt.models;

import java.util.Date;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private Date lastLogin;
    private String imagePath; // Thêm thuộc tính imagePath

    // Constructor mặc định
    public User() {
    }

    // Constructor đầy đủ
    public User(int id, String username, String password, String email, Date lastLogin, String imagePath) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.lastLogin = lastLogin;
        this.imagePath = imagePath;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}