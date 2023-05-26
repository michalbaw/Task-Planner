package app.taskplanner.viewmodel.boardviewmodel;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.Note;
import app.taskplanner.view.boardView.SimpleNoteController;
import app.taskplanner.viewmodel.SimpleNote;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.ViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.Stage;

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
        notes = dataModel.getNotes();
    }

    public ObservableList<Note> getNotes() {
        loadNotes();
        return notes;
    }

    public BoardViewModel() {

    }

    @Override
    public void closeWindow() {

    }
    void checkListMode(SimpleNoteController note,boolean val){
        notes.stream().filter(m -> m.getController().equals(note)).findAny().ifPresent(m -> m.checkListMode=val);
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
