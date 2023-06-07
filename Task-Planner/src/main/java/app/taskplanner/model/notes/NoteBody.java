package app.taskplanner.model.notes;

import java.io.Serializable;
import java.util.List;

public interface NoteBody extends Serializable {
    String getContent();
    void setContent(String newContent);
    List<NoteTask> getNoteTasks();
    void setNoteTasks(List<NoteTask> newList);
}
