package app.taskplanner.viewmodel.boardviewmodel;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.Task;
import app.taskplanner.model.notes.SimpleNote;
import app.taskplanner.service.ChangeModelService;
import app.taskplanner.service.NotificationService;
import app.taskplanner.view.boardView.SimpleNoteController;
//import app.taskplanner.viewmodel.NoteTasks;
//import app.taskplanner.viewmodel.SimpleNote;
import app.taskplanner.viewmodel.Handler;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.ViewModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.util.List;

public class BoardViewModel implements ViewModel {
    private ViewHandler viewHandler;
    private DataModel dataModel;
    private Stage stage;
    private ObservableList<Note> notes = FXCollections.observableArrayList();
    private ChangeModelService changeModelService;
    private NotificationService notificationService;

    public ListProperty<Note> boardNotes() { return new SimpleListProperty<Note>(notes);}

    public void init(Handler viewHandler, DataModel dataModel) {
        this.viewHandler = (ViewHandler) viewHandler;
        this.dataModel = dataModel;
        stage = new Stage();
    }


    public void init(Handler viewHandler, DataModel dataModel, Stage currentView) {
        this.viewHandler = (ViewHandler) viewHandler;
        this.dataModel = dataModel;
        this.stage = currentView;
    }

    private void loadNotes() {

    }

    public ObservableList<Note> getNotes() {
        loadNotes();
        return notes;
    }

    public BoardViewModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }


    public List<Task> getTasks(SimpleNoteController ctrl) {
        List<Task> tasks = null;

        return tasks;
    }


    public void resizeX(SimpleNoteController ctrl, double px) {
        ctrl.setX(ctrl.getX() + px);
    }

    public void resizeY(SimpleNoteController ctrl, double px) {
        ctrl.setY(ctrl.getY() + px);
    }
    public Note getNote(int x) {
       return null;
    }

    class NoteOnBoard {
        boolean checkListMode;
        Note note;
        SimpleNoteController controller;



        public void setNote(SimpleNote note) {
            this.note = note;
        }

        public SimpleNoteController getController() {
            return controller;
        }

        public void setController(SimpleNoteController controller) {
            this.controller = controller;
        }
    }

    public void refreshNotes() {
        //loadNotes();
    }
    public void init(ChangeModelService changeModelService, NotificationService notificationService) {
        this.changeModelService = changeModelService;
        this.notificationService = notificationService;
    }
}
