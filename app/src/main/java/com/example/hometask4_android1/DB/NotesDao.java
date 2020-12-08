package com.example.hometask4_android1.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hometask4_android1.Model.Notes;

import java.util.List;

@Dao
public interface NotesDao{
    //Добавление в бд
    @Insert void insertAll(Notes... notes);
    //Удаление из бд
    @Delete void delete(Notes notes);
    //Обновление бд
    @Update void update(Notes... notes);
    //Получение всех элеменов бд
    @Query("SELECT * FROM notes")
    List<Notes> getAllElements();

}
