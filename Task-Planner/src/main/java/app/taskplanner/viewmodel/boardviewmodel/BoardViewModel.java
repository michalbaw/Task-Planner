package app.taskplanner.viewmodel.boardviewmodel;

import app.taskplanner.model.DataModel;
import app.taskplanner.view.boardView.SimpleNoteController;
import app.taskplanner.viewmodel.NoteTasks;
import app.taskplanner.viewmodel.SimpleNote;
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
    public void init(ViewHandler viewHandler, DataModel dataModel) {
        this.viewHandler = viewHandler;
        this.dataModel = dataModel;
        stage = new Stage();
    }

    @Override
    public void init(ViewHandler viewHandler, DataModel dataModel, Stage currentView) {
        this.viewHandler = viewHandler;
        this.dataModel = dataModel;
        this.stage = currentView;
    }

    private void loadNotes() {
    //todo
//        for(SimpleNote note : dataModel.getNotes();
    }

    public ObservableList<SimpleNote> getNotes() {
        loadNotes();
        return (ObservableList<SimpleNote>) notes.stream().map(NoteOnBoard::getNote).toList();
    }

    public BoardViewModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    @Override
    public void closeWindow() {

    }
    public List<NoteTasks> getTasks(SimpleNoteController ctrl){
        return notes.stream().filter(m -> m.getController().equals(ctrl)).findAny().get().getNote().getTasks();
    }
    public void checkListMode(SimpleNoteController ctrl, boolean val){
        notes.stream().filter(m -> m.getController().equals(ctrl)).findAny().ifPresent(m -> m.checkListMode=val);
    }
    public void resizeX(SimpleNoteController ctrl, double px) {
        notes.stream().filter(m -> m.getController().equals(ctrl)).findAny().ifPresent(m -> m.getNote().setWidth(m.getNote().getWidth()+px));
    }
    public void resizeY(SimpleNoteController ctrl, double px) {
        notes.stream().filter(m -> m.getController().equals(ctrl)).findAny().ifPresent(m -> m.getNote().setHeight(m.getNote().getHeight()+px));
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
}
