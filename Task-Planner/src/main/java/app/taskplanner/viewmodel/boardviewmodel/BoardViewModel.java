package app.taskplanner.viewmodel.boardviewmodel;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.*;
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

import java.io.IOException;
import java.util.List;

public class BoardViewModel implements ViewModel {
    private ViewHandler viewHandler;
    private Stage stage;
    private DataModel dataModel;
    private final ObservableList<Note> notes = FXCollections.observableArrayList();
    private ChangeModelService changeModelService;
    private NotificationService notificationService;

    public BoardViewModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public ListProperty<Note> boardNotes() { return new SimpleListProperty<Note>(notes);}

    private void loadNotes() {
        List<NoteMetadata> notesMetadata = dataModel.getNotesMetadata();
        //System.out.println(notesMetadata.size());
        notes.clear();
        for(NoteMetadata note : notesMetadata){
            try{
                notes.add(dataModel.getNote(note.getKey()));
            }
            catch (IOException io)
            {
                System.err.println("prosze, uwolnijcie mnie");
            }
        }
    }

    public ObservableList<Note> getNotes() {
        loadNotes();
        return notes;
    }


    public List<SimpleTask> getTasks(SimpleNoteController ctrl) {
        List<SimpleTask> tasks = null;
        try {
            tasks= dataModel.getNote(ctrl.getSelfNote().getKey()).getNoteBody().getTasks();
        }
        catch (IOException ioioio) {
            System.err.println("mamo, zjadlem ta srebrna kulke z termometru");
        }
        return tasks;
    }

    public Note getNote(int x) {
        try {
            return dataModel.getNote(x);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //class NoteOnBoard {
    //    boolean checkListMode;
    //    Note note;
    //    SimpleNoteController controller;
    //    public void setNote(SimpleNote note) {
    //        this.note = note;
    //    }

    //    public SimpleNoteController getController() {
    //        return controller;
    //    }

    //    public void setController(SimpleNoteController controller) {
    //        this.controller = controller;
    //    }
    //}

    public void refreshNotes() {
        loadNotes();
    }
    public void init(ChangeModelService changeModelService, NotificationService notificationService) {
        this.changeModelService = changeModelService;
        this.notificationService = notificationService;
    }
}
