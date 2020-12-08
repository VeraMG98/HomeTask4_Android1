package com.example.hometask4_android1.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Notes implements Serializable {
    @PrimaryKey
    @NonNull private String title;
    private String note;
    private String date;

    public Notes(@NonNull String title, String note, String date) {
        this.title = title;
        this.note = note;
        this.date = date;
    }

    public Notes() {
        title = null;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
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

