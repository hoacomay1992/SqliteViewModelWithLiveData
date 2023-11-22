package com.example.sqliteviewmodelwithlivedata.database;

import com.example.sqliteviewmodelwithlivedata.database.entitys.Note;

import java.util.List;

public interface NoteCallback {
    void onAllDataLoaded(List<Note> notes);

    void onDataLoaded(Note note);
}
