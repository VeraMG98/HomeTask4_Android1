package com.example.hometask4_android1;

import java.io.Serializable;

public class Notes implements Serializable {
    private String note;
    private String date;

    public Notes(String note, String date) {
        this.note = note;
        this.date = date;
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
