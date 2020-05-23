package com.example.admin_demo;

public class Issue {
    private String BookId;
    private String Student;
    private String date;

   public Issue(String BookId,String student,String date)
    {
        this.BookId=BookId;
        this.Student=student;
        this.date=date;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getStudent() {
        return Student;
    }

    public void setStudent(String student) {
        Student = student;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
