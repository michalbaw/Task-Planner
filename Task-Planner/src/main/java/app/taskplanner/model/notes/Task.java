package app.taskplanner.model.notes;

public interface Task {
    void setTask(String task);
    String getTask();
    void setStatus(boolean isDone);
    boolean getStatus();

}
