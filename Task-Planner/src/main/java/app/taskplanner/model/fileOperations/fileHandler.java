package app.taskplanner.model.fileOperations;

import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.NoteMetadata;

import java.io.IOException;
import java.util.List;

public interface fileHandler {
    List<NoteMetadata> loadNotesMetadata() throws IOException;
    void saveNotesMetadata() throws IOException;
    Note loadNote (int key) throws IOException;
    void saveNote (Note noteToSave) throws IOException;
}
