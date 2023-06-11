package app.taskplanner.model.notes;

import java.io.Serializable;
import java.time.LocalDate;

public class SimpleNoteMetadata implements NoteMetadata, Serializable {
    private final int key;
    private String title;
    private LocalDate date;

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

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

}
