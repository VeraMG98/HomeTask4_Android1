package com.example.hometask4_android1.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.hometask4_android1.DB.AppDataBase;
import com.example.hometask4_android1.Model.Notes;
import com.example.hometask4_android1.databinding.ActivityAddBinding;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity{
    private ActivityAddBinding binding;
    AppDataBase dataBase;
    public Calendar date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        setInitialDate();
    }

    private void init() {
        dataBase = Room.databaseBuilder(this, AppDataBase.class, "mybd")
                .allowMainThreadQueries()
                .build();
        date = Calendar.getInstance();
    }

    public void onClickCalendarForAddActivity(View view) {
        new DatePickerDialog(AddActivity.this, d,
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void setInitialDate() {
        String dateText = DateUtils.formatDateTime(this, date.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR);
        binding.dateAddNew.setText(dateText);
    }

    public void onClickButtonAddActivity(View view) {
        if (TextUtils.isEmpty(binding.editNewElement.getText().toString()) ||
                TextUtils.isEmpty(binding.addNewNote.getText().toString())){
            Toast.makeText(AddActivity.this, "Пустая заметка", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Notes notes = new Notes(binding.editNewElement.getText().toString(),
                    binding.addNewNote.getText().toString(),
                    binding.dateAddNew.getText().toString());
            dataBase.getNotesDao().insertAll(notes);
            dataBase.getNotesDao().update(notes);
            binding.btnSave.setVisibility(View.GONE);
            Toast.makeText(AddActivity.this, "Сохранено", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            date.set(Calendar.YEAR, year);
            date.set(Calendar.MONTH, month);
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDate();
        }
    };

    public void onClickBackMain(View view) {
        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

