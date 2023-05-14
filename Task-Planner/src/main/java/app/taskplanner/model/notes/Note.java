package app.taskplanner.model.notes;

public interface Note {
    NoteMetadata getMetadata();
    void setMetadata(NoteMetadata metadata);
    NoteBody getNoteBody();
    void setNoteBody(NoteBody noteBody);
}
