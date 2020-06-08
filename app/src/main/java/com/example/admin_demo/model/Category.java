package com.example.admin_demo.model;

public class Category {
    private String author;
    private String bookId;
    private String category;
    private String issue_status;
    private String title;

    public Category() {
    }

    public Category(String author, String bookId, String category, String issue_status, String title) {
        this.author = author;
        this.bookId = bookId;
        this.category = category;
        this.issue_status = issue_status;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIssue_status() {
        return issue_status;
    }

    public void setIssue_status(String issue_status) {
        this.issue_status = issue_status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
