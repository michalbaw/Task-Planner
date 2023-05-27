package app.taskplanner.model.fileOperations;

import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.NoteBody;
import app.taskplanner.model.notes.NoteMetadata;

import java.io.IOException;
import java.util.List;

public interface FileHandler {
    List<NoteMetadata> loadNotesMetadata() throws IOException;
    void saveNotesMetadata() throws IOException;
    NoteBody loadBody (int key) throws IOException;
    void saveBody (int key, NoteBody body) throws IOException;
    void removeBody(int key) throws IOException;
}
