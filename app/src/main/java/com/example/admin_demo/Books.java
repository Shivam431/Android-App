package com.example.admin_demo;

public class Books {
    private String author;

    private String title;

    // constructors

    public Books() {}

    public Books(String username,String password) {

        this.author = username;

        this.title = password;

    }

    // properties



    public String getUsername() {

        return this.author;

    }

    public void setPassword(String password) {

        this. title= password;

    }

    public String getPassword() {

        return this.title;

    }

}


