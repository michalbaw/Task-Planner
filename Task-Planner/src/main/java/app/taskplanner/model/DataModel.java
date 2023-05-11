package app.taskplanner.model;

import java.io.IOException;
import java.util.List;

public interface DataModel {
    void loadNotes(String location) throws IOException;
    void saveNotes() throws IOException;
}
