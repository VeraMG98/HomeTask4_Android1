package com.example.hometask4_android1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.hometask4_android1.DB.AppDataBase;
import com.example.hometask4_android1.Model.Notes;
import com.example.hometask4_android1.databinding.ActivitySecondBinding;

import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {
    ActivitySecondBinding binding;
    public Calendar date;
    public static String dateText;
    public static String KEY = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        setInitialDate();
        Intent intent = getIntent();
        if (intent != null) {
            Notes notes = (Notes) intent.getSerializableExtra(MainActivity.KEY);
            binding.editNewElement1.setText(notes.getTitle());
            binding.addNewNote1.setText(notes.getNote());
            binding.dateAddNew1.setText(notes.getDate());
        }
    }

    private void init() {
        date = Calendar.getInstance();
    }

    public void onClickButtonOkSecondActivity(View view) {
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        Notes notes = new Notes(
                binding.editNewElement1.getText().toString(),
                binding.addNewNote1.getText().toString(),
                binding.dateAddNew1.getText().toString());
        MainActivity.dataBase.getNotesDao().update(notes);
        Toast.makeText(SecondActivity.this, "Сохранено", Toast.LENGTH_SHORT)
                .show();
        intent.putExtra(KEY, notes);
        setResult(RESULT_OK, intent);
    }

    private void setInitialDate() {
        dateText = DateUtils.formatDateTime(this, date.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR);
        binding.dateAddNew1.setText(dateText);
    }

    public void onClickCalendarIcon(View view) {
        new DatePickerDialog(SecondActivity.this, d,
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH)).show();
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
        finish();
    }

    public void onClickShareIconSecond(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, binding.editNewElement1.getText().toString() +
                "\n" + binding.addNewNote1.getText().toString());
        startActivity(intent);
    }
}