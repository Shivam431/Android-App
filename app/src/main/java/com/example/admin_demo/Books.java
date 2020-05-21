package com.example.admin_demo;

public class Books {
    private String author;
    private String title;
    private String bookId;
    private String category;
private String issue_status;
    // constructors

    public Books(String author,String title, String bookId,String category,String issue_status ) {

        this.author = author;
        this.title = title;
        this.bookId=bookId;
        this.category=category;
        this.issue_status=issue_status;

    }

    // properties


    public String getIssue_status() {
        return issue_status;
    }

    public void setIssue_status(String issue_status) {
        this.issue_status = issue_status;
    }

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


