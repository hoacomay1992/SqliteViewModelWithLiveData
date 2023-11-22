package com.example.sqliteviewmodelwithlivedata.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqliteviewmodelwithlivedata.repository.NoteRepository;


public class BaseActivity extends AppCompatActivity {
    protected NoteRepository noteRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteRepository = new NoteRepository(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
