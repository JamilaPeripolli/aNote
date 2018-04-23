package com.jamps.anote.model;

public class Note {

    private Long id;

    private String title;

    private String description;

    private boolean important;

    public Note(String title, String description, boolean important) {
        this.title = title;
        this.description = description;
        this.important = important;
    }

    public Note(Long id, String title, String description, boolean important) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.important = important;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isImportant() {
        return important;
    }

}
