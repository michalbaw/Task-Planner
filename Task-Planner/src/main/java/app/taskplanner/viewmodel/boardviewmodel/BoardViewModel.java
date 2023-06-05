package app.taskplanner.viewmodel.boardviewmodel;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.SimpleObservableList;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.model.notes.NoteTask;
import app.taskplanner.model.notes.SimpleNote;
import app.taskplanner.view.boardView.SimpleNoteController;
//import app.taskplanner.viewmodel.NoteTasks;
//import app.taskplanner.viewmodel.SimpleNote;
import app.taskplanner.viewmodel.Handler;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.ViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.util.List;

public class BoardViewModel implements ViewModel {
    ViewHandler viewHandler;
    DataModel dataModel;
    Stage stage;
    ObservableList<NoteOnBoard> notes = FXCollections.observableArrayList();

    @Override
    public void init(Handler viewHandler, DataModel dataModel) {
        this.viewHandler = (ViewHandler) viewHandler;
        this.dataModel = dataModel;
        stage = new Stage();
    }

    @Override
    public void init(Handler viewHandler, DataModel dataModel, Stage currentView) {
        this.viewHandler = (ViewHandler) viewHandler;
        this.dataModel = dataModel;
        this.stage = currentView;
    }

    private void loadNotes() {
        //todo
//        for(SimpleNote note : dataModel.getNotes();
    }

    public ObservableList<SimpleNote> getNotes() {
        loadNotes();
//        return (ObservableList<SimpleNote>) notes.stream().map(NoteOnBoard::getNote).toList();
        return new SimpleObservableList<>();
    }

    public BoardViewModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    @Override
    public void closeWindow() {

    }

    public List<NoteTask> getTasks(SimpleNoteController ctrl) {
        return notes.stream().filter(m -> m.getController().equals(ctrl)).findAny().get().getNote().getNoteBody().getNoteTasks();
    }

    public void checkListMode(SimpleNoteController ctrl, boolean val) {
        notes.stream().filter(m -> m.getController().equals(ctrl)).findAny().ifPresent(m -> m.checkListMode = val);
    }

    public void resizeX(SimpleNoteController ctrl, double px) {
        ctrl.setX(ctrl.getX() + px);
    }

    public void resizeY(SimpleNoteController ctrl, double px) {
        ctrl.setY(ctrl.getY() + px);
    }

    class NoteOnBoard {
        boolean checkListMode;
        SimpleNote note;
        SimpleNoteController controller;

        public SimpleNote getNote() {
            return note;
        }

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

    }
}
