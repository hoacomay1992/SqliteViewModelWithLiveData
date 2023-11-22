package com.example.sqliteviewmodelwithlivedata.repository;

import android.content.Context;
import android.util.Log;


import com.example.sqliteviewmodelwithlivedata.database.NoteCallback;
import com.example.sqliteviewmodelwithlivedata.database.NoteRoomDatabase;
import com.example.sqliteviewmodelwithlivedata.database.dao.NoteDao;
import com.example.sqliteviewmodelwithlivedata.database.entitys.Note;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class NoteRepository {
    private final NoteDao noteDao;
    private final ExecutorService executorService;

    public NoteRepository(Context context) {
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(context);
        noteDao = db.noteDao();
        executorService = NoteRoomDatabase.databaseWriteExecutor;
    }

    public void insertNote(final Note note) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.insert(note);
            }
        });
    }

    public void deleteNote(final Note note) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.delete(note);
            }
        });
    }

    public void getAllNote(final NoteCallback callback) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Note> dataList = noteDao.getAllNotes();
                callback.onAllDataLoaded(dataList);
            }
        });
    }

    public void findNoteByTitle(String title, NoteCallback callback) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Note note = noteDao.findNoteByTitle(title);
                callback.onDataLoaded(note);
            }
        });

    }

    public void shutdownExecutor() {
        Log.d("HAU", "shutdownExecutor success");
        executorService.shutdown();
    }
}
