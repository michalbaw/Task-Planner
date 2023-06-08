package app.taskplanner.model.notes;

import java.util.LinkedList;
import java.util.List;

public class SimpleNoteBody implements NoteBody {
    String content;
    List<Task> tasks;

    public SimpleNoteBody(){
        tasks = new LinkedList<>();
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public List<Task> getNoteTasks() {
        return tasks;
    }

    @Override
    public void setNoteTasks(List<Task> newList) {
        this.tasks = newList;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}
