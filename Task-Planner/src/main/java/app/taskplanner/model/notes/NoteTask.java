package app.taskplanner.model.notes;

public interface NoteTask {
    NoteTask getTask();
    String getTaskTitle();
    void setTaskTitle(String title);
    void setStatus(boolean isDone);
    boolean getStatus();

}
