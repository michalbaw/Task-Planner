package app.taskplanner.model.notes;

public class SimpleNoteTask implements NoteTask{
    String title;
    String task;
    boolean status;
    @Override
    public String getTask() {
        return task;
    }

    @Override
    public String getTaskTitle() {
        return title;
    }

    @Override
    public void setTaskTitle(String newTitle) {
        this.title = title;
    }

    @Override
    public void setStatus(boolean isDone) {
        status = isDone;
    }

    @Override
    public boolean getStatus() {
        return status;
    }
}
