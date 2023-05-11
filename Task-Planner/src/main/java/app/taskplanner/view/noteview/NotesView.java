package com.example.demo.view;

import java.util.List;

public class NotesView {
    private List<Note> notes;
    public void addNote(Note note)
    {
        notes.add(note);
    }
    public List<Note> getNotes(){
        return notes;
    }
    public void setNotes(List<Note> notes){
        this.notes = notes;
    }
}
