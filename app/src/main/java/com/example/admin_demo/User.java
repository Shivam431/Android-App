package com.example.admin_demo;
public class User {

    // fields

    private String username;

    private String password;

    private String user_type="student";

    // constructors

    public User() {}

    public User(String username,String password) {

        this.username = username;

        this.password = password;

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

}
