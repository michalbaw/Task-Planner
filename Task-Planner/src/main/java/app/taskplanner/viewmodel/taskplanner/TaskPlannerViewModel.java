package app.taskplanner.viewmodel.taskplanner;


import javafx.beans.property.StringProperty;
import app.taskplanner.model.DataModel;
import app.taskplanner.model.Note;
import java.util.List;


public class TaskPlannerViewModel {

    List<Note> notes;

    private DataModel model;

    public TaskPlannerViewModel(DataModel model) {
        this.model = model;
    }
    public void upadateTaskPlanner() {
        notes = model.getNotes();

    }
    public StringProperty notesProperty() { return null; }

}

