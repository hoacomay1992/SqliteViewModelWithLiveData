package com.example.sqliteviewmodelwithlivedata.adapters;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sqliteviewmodelwithlivedata.R;
import com.example.sqliteviewmodelwithlivedata.database.NoteCallback;
import com.example.sqliteviewmodelwithlivedata.database.entitys.Note;
import com.example.sqliteviewmodelwithlivedata.repository.NoteRepository;

import java.util.ArrayList;
import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> implements NoteCallback {
    private Context context;
    private NoteRepository repository;

    private LayoutInflater inflater;
    private List<Note> notes = new ArrayList();
    private Handler uiHandler = new Handler(Looper.getMainLooper());

    public NoteListAdapter(Context context, NoteRepository repository) {
        this.context = context;
        this.repository = repository;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.titleItemView.setText(currentNote.title);
        holder.contentItemView.setText(currentNote.content);
        holder.deleteItemView.setOnClickListener(v -> {
            repository.deleteNote(currentNote);
            repository.getAllNote(this);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @Override
    public void onAllDataLoaded(List<Note> notes) {
        if (notes != null) {
            setNotes(notes);
        }
    }

    @Override
    public void onDataLoaded(Note note) {

    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView titleItemView;
        TextView contentItemView;
        Button deleteItemView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleItemView = itemView.findViewById(R.id.note_title);
            contentItemView = itemView.findViewById(R.id.note_content);
            deleteItemView = itemView.findViewById(R.id.button_delete);
        }

    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }
}
