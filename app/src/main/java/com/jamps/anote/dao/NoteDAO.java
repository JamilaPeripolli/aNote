package com.jamps.anote.dao;

import android.content.Context;

import com.jamps.anote.model.Note;

import java.util.List;

public interface NoteDAO {

    public void add(Context context, Note note);

    public void delete(Context context, Long id);

    public List<Note> getAll(Context context);

}
