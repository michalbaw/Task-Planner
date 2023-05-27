package app.taskplanner.viewmodel;

import javafx.beans.property.*;

public class NoteTasks {
    private static Integer counter;
    StringProperty title;
    BooleanProperty toDo;
    IntegerProperty id;

    NoteTasks(String title){
        this.id = new SimpleIntegerProperty(counter++);
        this.title= new SimpleStringProperty(title);
        this.toDo = new SimpleBooleanProperty(false);
    }
    public final BooleanProperty toDoProperty() {
        return this.toDo;
    }
    public final StringProperty titleProperty() {
        return this.title;
    }
    public final String titleAsString() {
        return this.title.toString();
    }
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj)
            return true;
        if(obj == null)
            return false;
        if(obj.getClass() != this.getClass())
            return false;
        NoteTasks nTasks = (NoteTasks) obj;
        return nTasks.id == this.id;
    }
}