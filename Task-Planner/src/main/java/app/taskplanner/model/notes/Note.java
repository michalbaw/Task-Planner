package app.taskplanner.model.notes;

public interface Note {
    NoteMetadata getMetadata();
    String getNoteBody();
    void setNoteBody(String noteBody);
}
