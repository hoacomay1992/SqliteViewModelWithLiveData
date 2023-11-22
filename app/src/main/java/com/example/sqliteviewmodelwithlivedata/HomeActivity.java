package com.example.sqliteviewmodelwithlivedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.sqliteviewmodelwithlivedata.adapters.NoteListAdapter;
import com.example.sqliteviewmodelwithlivedata.base.BaseActivity;
import com.example.sqliteviewmodelwithlivedata.database.NoteCallback;
import com.example.sqliteviewmodelwithlivedata.database.entitys.Note;
import com.example.sqliteviewmodelwithlivedata.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements NoteCallback {
    private NoteListAdapter adapter;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new NoteListAdapter(HomeActivity.this, noteRepository);
        binding.recyclerNotes.setAdapter(adapter);
        binding.recyclerNotes.setLayoutManager(new LinearLayoutManager(this));
        binding.buttonNewNote.setOnClickListener(v -> {
            Intent newNoteIntent = new Intent(this, NewNoteActivity.class);
            startActivity(newNoteIntent);
        });
        binding.buttonFind.setOnClickListener(v -> {
            findNote();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllNotes();
    }

    private void findNote() {
        String strFind = binding.edittextFind.getText().toString();
        if (!TextUtils.isEmpty(strFind)) {
            noteRepository.findNoteByTitle(strFind, this);
        } else {
            getAllNotes();
        }

    }

    private void getAllNotes() {
        noteRepository.getAllNote(this);
    }

    @Override
    public void onAllDataLoaded(List<Note> notes) {
        if (notes != null) {
            adapter.setNotes(notes);
        }
    }

    @Override
    public void onDataLoaded(Note note) {
        if (note != null) {
            List<Note> notes = new ArrayList<>();
            notes.add(note);
            adapter.setNotes(notes);
        }
    }
}