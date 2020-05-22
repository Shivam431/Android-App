package com.example.admin_demo;
public class User {

    // fields

    private String username;

    private String password;
    private String user_type;

    // constructors

    public User() {}

    public User(String username,String password,String user_type) {

        this.username = username;

        this.password = password;
        this.user_type=user_type;

    }

    // properties

    public void setUsername(String username) {

        this.username = username;

    }

    public String getUsername() {

        return this.username;

    }

    public void setPassword(String password) {

        this.password = password;

    }

    public String getPassword() {

        return this.password;

    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_type() {
        return user_type;
    }
}