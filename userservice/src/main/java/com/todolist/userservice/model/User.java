package com.todolist.userservice.model;

public class User {
    private String id;
    private String fullname;
    private String email;
    private long lastLogin;

    public User(String id, String fullname, String email, long lastLogin) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.lastLogin = lastLogin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
    }
}
