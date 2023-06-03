package app.taskplanner.model.notes;

import java.io.Serializable;

public class SimpleNoteMetadata implements NoteMetadata, Serializable {
    private int key;
    private String title;

    @Override
    public int getKey() {
        return key;
    }

    public SimpleNoteMetadata(int key) {
        this.key = key;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }
}
