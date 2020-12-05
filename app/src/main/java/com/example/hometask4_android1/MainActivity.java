package com.example.hometask4_android1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hometask4_android1.Adapters.FirstAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickItems {
    public RecyclerView recyclerView;
    public FirstAdapter firstAdapter;
    public List<String> list;
    public Integer positionCurrent = null;
    public static final String EXTRA_TEXT1 = "text";

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
        for (int i = 0; i < 10; i++) {
            list.add(i + " Add item");
        }
        firstAdapter = new FirstAdapter(list, this, this);
        recyclerView.setAdapter(firstAdapter);
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

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.this.onClickItems(viewHolder.getAdapterPosition());
                }
            });
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            firstAdapter.list.remove(viewHolder.getAdapterPosition());
            firstAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    };

    @Override
    public void onClickItems(int position) {
        positionCurrent = position;
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(SecondActivity.EXTRA_TEXT, list.get(position));
        startActivityForResult(intent, position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == positionCurrent && resultCode == RESULT_OK && data != null) {
            String text = data.getStringExtra(SecondActivity.EXTRA_TEXT1);
            firstAdapter.update(requestCode, text);
        } else {
            Toast.makeText(this, "fall", Toast.LENGTH_SHORT).show();
        }
    }
}