package com.example.hometask4_android1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hometask4_android1.Model.Notes;
import com.example.hometask4_android1.R;

import java.util.List;

public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.FirstViewHolder> {
    public List<Notes> list;
    public Context context;
    ItemClickListener listener;

    public FirstAdapter(List<Notes> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setElement(Notes notes, int position) {
        list.set(position, notes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FirstViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new FirstViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FirstViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class FirstViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        TextView title, note, date;
        ImageView buttonMenu;
        Notes notes;

        public FirstViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title_note);
            note = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date_item_list);
            buttonMenu = itemView.findViewById(R.id.image_item_menu);
            buttonMenu.setOnClickListener(v -> {
                PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(this);
                popupMenu.show();
            });
        }

        public void onBind(Notes notes) {
            this.notes = notes;
            title.setText(notes.getTitle());
            note.setText(notes.getNote());
            date.setText(notes.getDate());
        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onItemClick(notes, getAdapterPosition());
            }
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (item.getItemId() == R.id.delete) {
                list.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
                return true;
            }
            return false;
        }
    }

    public void setOnClickListener(ItemClickListener clickListener) {
        this.listener = clickListener;
    }
}


