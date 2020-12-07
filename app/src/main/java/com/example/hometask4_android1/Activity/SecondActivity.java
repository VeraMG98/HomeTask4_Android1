package com.example.hometask4_android1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hometask4_android1.Notes;
import com.example.hometask4_android1.R;

import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {
    public Calendar date;
    public static String dateText;
    public EditText editText;
    public TextView textViewDate;
    public static String KEY = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
        setInitialDate();
        Intent intent = getIntent();
        if (intent != null) {
            Notes notes = (Notes) intent.getSerializableExtra(MainActivity.KEY);
            editText.setText(notes.getNote());
            textViewDate.setText(notes.getDate());
        }
    }

    private void init() {
        editText = findViewById(R.id.edit_theme);
        textViewDate = findViewById(R.id.text_for_date);
        date = Calendar.getInstance();
    }

    public void onClickButtonOkSecondActivity(View view) {
        Intent intent = new Intent();
        Notes notes = new Notes(editText.getText().toString(),
                textViewDate.getText().toString());
        intent.putExtra(KEY, notes);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void setInitialDate() {
        dateText = DateUtils.formatDateTime(this, date.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR);
        textViewDate.setText(dateText);
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
}