package app.taskplanner.model.notes;

import java.io.Serializable;

public interface NoteMetadata {
    int getKey();
    String getTitle();
    void setTitle(String newTitle);
}
