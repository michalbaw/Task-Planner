package app.taskplanner.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NoteTasks {
    StringProperty title = new SimpleStringProperty();
    BooleanProperty toDo = new SimpleBooleanProperty();

    NoteTasks(String title){
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
}