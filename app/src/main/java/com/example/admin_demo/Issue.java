package com.example.admin_demo;

public class Issue {
    private String BookId;
    private String Student;
    private String date;
    private String return_date;

   public Issue(String BookId,String date ,String return_date,String student)
    {

        this.BookId=BookId;
        this.date=date;
        this.return_date=return_date;
        this.Student=student;
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

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }
}
