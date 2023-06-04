package app.taskplanner.model.notes;

import java.util.List;

public class SimpleNoteBody implements NoteBody {
    String content;
    List<NoteTask> tasks;

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public List<NoteTask> getNoteTasks() {
        return tasks;
    }

    @Override
    public void setNoteTasks(List<NoteTask> newList) {
        this.tasks = newList;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}
