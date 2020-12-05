package com.example.hometask4_android1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hometask4_android1.OnClickItems;
import com.example.hometask4_android1.R;

import java.util.List;

public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.FirstViewHolder> {
    public List<String> list;
    public Context context;
    public OnClickItems onClickItems;


    public FirstAdapter(List<String> list, Context context, OnClickItems onClickItems) {
        this.list = list;
        this.context = context;
        this.onClickItems = onClickItems;
    }

    @NonNull
    @Override
    public FirstViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_list, parent, false);
        return new FirstViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FirstViewHolder holder, int position) {
        holder.theme.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(int requestCode, String text) {
        list.add(requestCode, text);
        notifyDataSetChanged();
    }

    class FirstViewHolder extends RecyclerView.ViewHolder {
        public TextView theme;

        public FirstViewHolder(@NonNull View itemView) {
            super(itemView);
            theme = itemView.findViewById(R.id.name);
            itemView.setOnClickListener(v ->
                    onClickItems.onClickItems(getAdapterPosition()));
            theme.setOnClickListener(v ->
                    onClickItems.onClickItems(getAdapterPosition()));
        }
    }
}


