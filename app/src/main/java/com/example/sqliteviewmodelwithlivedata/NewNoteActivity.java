package com.example.sqliteviewmodelwithlivedata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sqliteviewmodelwithlivedata.base.BaseActivity;
import com.example.sqliteviewmodelwithlivedata.database.entitys.Note;
import com.example.sqliteviewmodelwithlivedata.databinding.ActivityNewNoteBinding;

public class NewNoteActivity extends BaseActivity {

    private ActivityNewNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonSave.setOnClickListener(v -> {
            String strTitle = binding.titleNote.getText().toString();
            String strContent = binding.contentNote.getText().toString();
            noteRepository.insertNote(new Note(strTitle, strContent));
            finish();
        });
    }
}