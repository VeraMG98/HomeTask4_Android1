package com.example.hometask4_android1.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hometask4_android1.Adapters.FirstAdapter;
import com.example.hometask4_android1.Adapters.ItemClickListener;
import com.example.hometask4_android1.DB.AppDataBase;
import com.example.hometask4_android1.Model.Notes;
import com.example.hometask4_android1.databinding.ActivityMainBinding;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {
    public static AppDataBase dataBase;
    private ActivityMainBinding binding;
    public FirstAdapter firstAdapter;
    public List<Notes> list;
    public static String KEY = "key";
    public static int REQUEST_CODE = 1;
    public int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
    }

    private void init() {
        dataBase = Room.databaseBuilder(this, AppDataBase.class, "mybd")
                .allowMainThreadQueries()
                .build();
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        list = dataBase.getNotesDao().getAllElements();
        firstAdapter = new FirstAdapter(list,this, dataBase);
        binding.recycler.setAdapter(firstAdapter);
        firstAdapter.setOnClickListener(this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(binding.recycler);
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
            dataBase.getNotesDao().delete(list.get(viewHolder.getAdapterPosition()));
            dataBase.getNotesDao().update(list.get(viewHolder.getAdapterPosition()));
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
            dataBase.getNotesDao().update(notes);
            firstAdapter.setElement(notes, position);
        }
    }

    public void onClickButtonAddActivity(View view) {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(intent);
        finish();
    }
}