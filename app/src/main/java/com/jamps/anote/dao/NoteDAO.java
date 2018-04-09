package com.jamps.anote.dao;

import com.jamps.anote.model.Note;

import java.util.List;

public interface NoteDAO {

    public void add(Note note);

    public void delete(Long id);

    public List<Note> getAll();

}
