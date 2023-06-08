package app.taskplanner.model.notes;

import java.util.ArrayList;
import java.util.List;

public class SimpleNoteBody implements NoteBody {
    String content;
    List<SimpleTask> tasks;

    public SimpleNoteBody(){
        tasks = new ArrayList<>();
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public List<SimpleTask> getTasks() {
        return tasks;
    }
    @Override
    public void addTask(SimpleTask task){
        tasks.add(task);
    }
    @Override
    public void clearTasks(){
        tasks.clear();
    }

    @Override
    public void setTasks(List<SimpleTask> newList) {
        this.tasks = newList;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}
