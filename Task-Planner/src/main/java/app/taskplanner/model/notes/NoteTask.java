package app.taskplanner.model.notes;

public interface NoteTask {
    String getTask();
    String getTaskTitle();
    void setTaskTitle(String newTitle);
    void setStatus(boolean isDone);
    boolean getStatus();

}
