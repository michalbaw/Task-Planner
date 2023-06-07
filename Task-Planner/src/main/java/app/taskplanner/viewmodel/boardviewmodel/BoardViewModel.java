package app.taskplanner.viewmodel.boardviewmodel;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.SimpleObservableList;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.model.notes.NoteTask;
import app.taskplanner.model.notes.SimpleNote;
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

import java.io.IOException;
import java.util.List;

public class BoardViewModel implements ViewModel {
    private ViewHandler viewHandler;
    private DataModel dataModel;
    private Stage stage;
    private ObservableList<Note> notes = FXCollections.observableArrayList();

    public ListProperty<Note> boardNotes() { return new SimpleListProperty<Note>(notes);}
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
        List<NoteMetadata> notesMetadata = dataModel.getNotesMetadata();
        for(NoteMetadata note : notesMetadata){
            try{
            notes.add(dataModel.openNote(note.getKey()));
            }
            catch (IOException ioioio)
            {
                System.err.println("prosze, uwolnijcie mnie");
            }
        }
    }

    public ObservableList<Note> getNotes() {
        loadNotes();
        return notes;
    }

    public BoardViewModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    @Override
    public void closeWindow() {

    }

    public List<NoteTask> getTasks(SimpleNoteController ctrl) {
        List<NoteTask> tasks = null;
        try {
            tasks= dataModel.openNote(ctrl.getSelfNote().getKey()).getNoteBody().getNoteTasks();
        }
        catch (IOException ioioio) {
            System.err.println("mamo, zjadlem ta srebrna kulke z termometru");
        }
        return tasks;
    }


    public void resizeX(SimpleNoteController ctrl, double px) {
        ctrl.setX(ctrl.getX() + px);
    }

    public void resizeY(SimpleNoteController ctrl, double px) {
        ctrl.setY(ctrl.getY() + px);
    }
    public Note getNote(int x) {
        try {
            return dataModel.openNote(x);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        loadNotes();

    }
}
