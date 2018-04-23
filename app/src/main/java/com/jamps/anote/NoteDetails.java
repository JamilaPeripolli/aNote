package com.jamps.anote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.jamps.anote.R;
import com.jamps.anote.dao.NoteDAO;
import com.jamps.anote.dao.NoteDAOImpl;

public class NoteDetails extends AppCompatActivity {

    private Long id;
    private String title;
    private String description;
    private boolean isImportant;
    private NoteDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        dao = NoteDAOImpl.getInstance();
        getNoteDetails(getIntent().getExtras());
        fillNoteDetails();
    }

    private void getNoteDetails(Bundle bundle) {
        id = bundle.getLong("id");
        title = bundle.getString("title");
        description = bundle.getString("description");
        isImportant = bundle.getBoolean("isImportant");
    }

    private void fillNoteDetails() {
        TextView tvTitle = findViewById(R.id.tv_title_detail);
        TextView tvDescription = findViewById(R.id.tv_description_detail);
        ImageView ivImportantWarning = findViewById(R.id.iv_important_detail);

        tvTitle.setText(title);
        tvDescription.setText(description);

        if(!isImportant) {
            ivImportantWarning.setVisibility(View.INVISIBLE);
        }
    }

    public void back(View view) {
        finish();
    }

    public void removeNote(View view) {
        dao.delete(this, id);
        finish();
    }

}
