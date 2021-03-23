package com.example.notesj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.notesj.db.NotesContract;
import com.example.notesj.db.NotesDBHelper;
import com.example.notesj.db.NotesDatabase;
import com.example.notesj.notes.Note;
import com.example.notesj.viewmodel.MainViewModel;

public class AddNoteActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinnerDaysOfWeek;
    private RadioGroup radioGroupPriority;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        spinnerDaysOfWeek = findViewById(R.id.spinnerDayOfWeek);
        radioGroupPriority = findViewById(R.id.radioGroupPriority);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    public void onClickSaveNote(View view) {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int dayOfWeek = spinnerDaysOfWeek.getSelectedItemPosition();
        RadioButton radioButton = findViewById(radioGroupPriority.getCheckedRadioButtonId());
        int priority = Integer.parseInt(radioButton.getText().toString());
        if (isField(title, description)) {
            Note note = new Note(title, description, dayOfWeek, priority);
            viewModel.insertNote(note);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else Toast.makeText(this, R.string.warning_fill_fields, Toast.LENGTH_LONG).show();
    }

    private boolean isField(String title, String description) {
        return !title.isEmpty() && !description.isEmpty();
    }
}