package com.example.admin_demo;


public class Books {
    private String author;
    private String title;
    private String bookId;
    private String category;

    // constructors

    public Books(String author,String title, String bookId,String category ) {

        this.author = author;
        this.title = title;
        this.bookId=bookId;
        this.category=category;
    }

    // properties



    public String getAuthor() {

        return this.author;

    }

    public void setAuthor(String a) {

        this.author= a;

    }

    public String getTitle() {

        return this.title;

    }

    public void setTitle(String title) {

        this.title= title;

    }

    public String getBookId() {

        return this.bookId;

    }

    public void setBookId(String b) {

        this.bookId= b;

    }

    public String getCategory() {

        return this.category;

    }

    public void setCategory(String category) {

        this.category= category;
    }

}
