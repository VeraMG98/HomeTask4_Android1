package com.example.hometask4_android1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT = "button";
    public static final String EXTRA_TEXT1 = "text";
    public EditText editText;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        String text = intent.getStringExtra(EXTRA_TEXT);
        editText = findViewById(R.id.edit_theme);
        editText.setText(text);
        button = findViewById(R.id.btn_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMainActivity();
            }
        });
    }

    private void sendMainActivity() {
        String text = editText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_TEXT1, text);
        setResult(RESULT_OK, intent);
        finish();
    }
}