package app.taskplanner.model.notes;

import java.util.List;

public interface Note {
    NoteMetadata getMetadata();
    void setMetadata(NoteMetadata metadata);
    NoteBody getNoteBody();
    void setNoteBody(NoteBody noteBody);
}
