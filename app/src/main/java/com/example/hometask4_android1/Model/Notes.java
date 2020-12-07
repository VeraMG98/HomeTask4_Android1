package com.example.hometask4_android1.Model;

import java.io.Serializable;

public class Notes implements Serializable {
    private String title;
    private String note;
    private String date;

    public Notes(String title, String note, String date) {
        this.title = title;
        this.note = note;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
