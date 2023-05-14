package app.taskplanner.model;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.NoteMetadata;

import java.io.IOException;
import java.util.List;

public interface DataModel {
    List<NoteMetadata> getNotesMetadata();
    void saveAll() throws IOException;
    void saveNote(int key) throws IOException;
    void addNote (String title) throws IOException;
    void removeNote (int key) throws IOException;
    Note openNote (int key) throws IOException;
    void closeNote (int key) throws IOException;
}
