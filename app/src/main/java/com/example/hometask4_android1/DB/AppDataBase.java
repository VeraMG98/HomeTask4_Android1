package com.example.hometask4_android1.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.hometask4_android1.Model.Notes;

@Database(entities = {Notes.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract NotesDao getNotesDao();
}
