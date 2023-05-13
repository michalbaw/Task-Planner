package app.taskplanner.model.notes;

public interface NoteMetadata {
    int getKey();
    String getTitle();
    void setTitle(String newTitle);
}
