package com.jamps.anote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jamps.anote.adapter.NoteAdapter;
import com.jamps.anote.dao.NoteDAO;
import com.jamps.anote.dao.NoteDAOImpl;
import com.jamps.anote.model.Note;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private final String TAG = "MainActivity";
    private ListView lvNotes;
    private NoteDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        fillNotesList();
    }

    private void fillNotesList() {
        dao = NoteDAOImpl.getInstance();
        List<Note> notes = dao.getAll();

        NoteAdapter adapter = new NoteAdapter(this, notes);

        lvNotes = findViewById(R.id.lv_notes);
        lvNotes.setAdapter(adapter);
        lvNotes.setOnItemClickListener(this);
    }

    public void addNote(View view) {
        Intent intent = new Intent(this, AddNote.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, NoteDetails.class);
        Note note = (Note) parent.getItemAtPosition(position);
        intent.putExtra("id", note.getId());
        intent.putExtra("title", note.getTitle());
        intent.putExtra("description", note.getDescription());
        intent.putExtra("isImportant", note.isImportant());
        startActivity(intent);
    }
}
