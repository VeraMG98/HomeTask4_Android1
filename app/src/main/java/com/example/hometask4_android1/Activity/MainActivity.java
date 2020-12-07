package com.example.hometask4_android1.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.hometask4_android1.Adapters.FirstAdapter;
import com.example.hometask4_android1.Model.Notes;
import com.example.hometask4_android1.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FirstAdapter.ItemClickListener{
    public RecyclerView recyclerView;
    public FirstAdapter firstAdapter;
    public List<Notes> list;
    public static String KEY = "key";
    public static int REQUEST_CODE = 1;
    public int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Notes("Title " + i,i + " Note", "Date"));
        }
        firstAdapter = new FirstAdapter(list, this);
        recyclerView.setAdapter(firstAdapter);
        firstAdapter.setOnClickListener(this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper
            .SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView,
                              @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder target) {
            int positionDrag = viewHolder.getAdapterPosition();
            int positionTarget = target.getAdapterPosition();
            Collections.swap(firstAdapter.list, positionDrag, positionTarget);
            firstAdapter.notifyItemMoved(positionDrag, positionTarget);
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            firstAdapter.list.remove(viewHolder.getAdapterPosition());
            firstAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    };

    @Override
    public void onItemClick(Notes notes, int position) {
        this.position = position;
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(KEY, notes);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Notes notes = (Notes) data.getSerializableExtra(SecondActivity.KEY);
            firstAdapter.setElement(notes, position);
        }
    }

}