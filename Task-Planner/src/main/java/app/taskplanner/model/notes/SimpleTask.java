package app.taskplanner.model.notes;

import java.io.Serializable;

public class SimpleTask implements Task, Serializable {
    String task;
    boolean status;

    public SimpleTask(String newTaskName, boolean b) {
        this.task = newTaskName;
        this.status = b;
    }

    @Override
    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String getTask() {
        return task;
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
