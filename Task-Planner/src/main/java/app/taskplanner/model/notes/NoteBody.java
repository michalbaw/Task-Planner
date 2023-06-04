package app.taskplanner.model.notes;

import java.util.List;

public interface NoteBody {
    String getContent();
    void setContent(String newContent);
    List<NoteTask> getNoteTasks();
    void setNoteTasks(List<NoteTask> newList);
}
