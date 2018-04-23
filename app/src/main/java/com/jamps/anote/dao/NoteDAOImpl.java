package com.jamps.anote.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jamps.anote.config.DBConfig;
import com.jamps.anote.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteDAOImpl implements NoteDAO{

    private static NoteDAOImpl instance;

    private SQLiteDatabase db;

    static {
        instance = new NoteDAOImpl();
    }

    public static NoteDAOImpl getInstance() {
        return instance;
    }

    private NoteDAOImpl() {
    }

    @Override
    public void add(Context context, Note note) {
        prepareDatabase(context);

        ContentValues values = new ContentValues();
        values.put("title", note.getTitle());
        values.put("description", note.getDescription());
        values.put("important", note.isImportant());

        db.insert("note", null, values);
    }

    @Override
    public void delete(Context context, Long id) {
        prepareDatabase(context);

        db.delete("note", "id = " + id, null);
    }

    @Override
    public List<Note> getAll(Context context) {
        prepareDatabase(context);

        List<Note> allNotes = new ArrayList<>();
        allNotes.addAll(getNotes(true));
        allNotes.addAll(getNotes(false));

        return allNotes;
    }

    private List<Note> getNotes(boolean importantNotes) {
        List<Note> notes = new ArrayList<>();
        String[] columns = {"id", "title", "description", "important"};
        Cursor cursor = db.query("note", columns, "important = ?", new String[]{(importantNotes ? "1" : "0")}, null, null, null);

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i ++) {
            Long id = cursor.getLong(0);
            String title = cursor.getString(1);
            String description = cursor.getString(2);
            boolean important = (new Integer(0).equals(cursor.getInt(3)) ? Boolean.FALSE : Boolean.TRUE);
            notes.add(new Note(id, title, description, important));
        }

        return notes;
    }

    private void prepareDatabase(Context context) {
        if (db == null) {
            db = new DBConfig(context).getWritableDatabase();
        }
    }
}
