package app.taskplanner.model.notes;

public interface Note {
    NoteMetadata getMetadata();
    NoteBody getNoteBody();
}
