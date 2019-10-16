package com.shawnshivere.farmdemo;

public class Note {
    String Title, Details, Date;

    public Note(){}

    public Note(String Title, String Details, String Date){
        this.Title = Title;
        this.Details = Details;
        this.Date = Date;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTitle() {
        return Title;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getDetails() {
        return Details;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDate() {
        return Date;
    }
}
