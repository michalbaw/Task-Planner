package app.taskplanner.model;

import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.NoteBody;
import app.taskplanner.model.notes.NoteMetadata;

import java.io.IOException;
import java.util.List;

public interface DataModel {
    List<NoteMetadata> getNotesMetadata();

    void saveNote(Note note) throws IOException;

    void addNote(String title) throws IOException;

    void removeNote(int key) throws IOException;

    Note getNote(int key) throws IOException;

    NoteMetadata getMetadata(int key);

    NoteBody getBody(int key) throws IOException;
}
