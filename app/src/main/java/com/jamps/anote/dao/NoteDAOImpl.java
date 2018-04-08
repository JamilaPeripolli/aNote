package com.jamps.anote.dao;

import com.jamps.anote.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteDAOImpl implements NoteDAO{

    private static NoteDAOImpl instance;

    private List<Note> notes;

    private List<Note> importantNotes;

    private Long idCounter;

    static {
        instance = new NoteDAOImpl();
    }

    public static NoteDAOImpl getInstance() {
        return instance;
    }

    private NoteDAOImpl() {
        notes = new ArrayList<>();
        importantNotes = new ArrayList<>();
        idCounter = 0L;
    }

    @Override
    public void add(Note note) {
        note.setId(++ idCounter);

        if(note.isImportant()) {
            importantNotes.add(note);
            return;
        }

        notes.add(note);
    }

    @Override
    public void delete(final Long id) {
        boolean removed = removeFromList(importantNotes, id);

        if(!removed) {
            removeFromList(notes, id);
        }
    }

    private boolean removeFromList(List<Note> notesList, Long id) {
        for (int i = 0; i < notesList.size(); i ++) {
            if (id.equals(notesList.get(i).getId())) {
                notesList.remove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public List<Note> getAll() {
        List<Note> allNotes = new ArrayList<>();

        allNotes.addAll(importantNotes);
        allNotes.addAll(notes);

        return allNotes;
    }
}
