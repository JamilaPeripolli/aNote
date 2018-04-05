package com.jamps.anote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.jamps.anote.dao.NoteDAO;
import com.jamps.anote.dao.NoteDAOImpl;
import com.jamps.anote.model.Note;

public class AddNote extends AppCompatActivity {

    private final String TAG = "AddNote";

    private NoteDAO noteDAO = NoteDAOImpl.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }

    public void save(View view) {
        noteDAO.add(extractNote(view));
        Log.i(TAG, "Note added successfully!");
        finish();
    }

    private Note extractNote(View view) {
        EditText etTitle = findViewById(R.id.et_title);
        EditText etDescription = findViewById(R.id.et_description);
        CheckBox cbImportant = findViewById(R.id.cb_important);

        return new Note(etTitle.getText().toString(), etDescription.getText().toString(), cbImportant.isActivated());
    }
}
