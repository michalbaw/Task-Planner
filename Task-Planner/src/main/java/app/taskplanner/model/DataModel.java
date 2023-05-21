package app.taskplanner.model;
import javafx.collections.ObservableList;
import java.io.IOException;

public interface DataModel {
    void loadNotes() throws IOException;
    void saveNotes() throws IOException;
    ObservableList<Note> getNotes();
    Note addNote (String title);
    void removeNote (Note toRemove);
}
