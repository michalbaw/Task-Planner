package app.taskplanner.viewmodel.boardviewmodel;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.*;
import app.taskplanner.service.ChangeModelService;
import app.taskplanner.service.NotificationService;
import app.taskplanner.view.boardView.BoardViewController;
import app.taskplanner.view.boardView.SimpleNoteController;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.ViewModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class BoardViewModel implements ViewModel {


    private final ListProperty<Note> notesProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final DataModel dataModel;
    private final ViewHandler viewHandler;
    private ChangeModelService changeModelService;
    private NotificationService notificationService;
    private BoardViewController boardViewController;

    public ListProperty<Note> notesProperty() {
        return notesProperty;
    }

    public ObservableList<Note> getNotes() {
        return notesProperty.get();
    }

    public BoardViewModel(DataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
    }

    public void refreshNotes() {
        List<NoteMetadata> notesMetadata = dataModel.getNotesMetadata();
        notesProperty.clear();
        for(NoteMetadata note : notesMetadata){
            try{
                notesProperty.add(dataModel.getNote(note.getKey()));
            }
            catch (IOException io)
            {
                System.err.println("prosze, uwolnijcie mnie");
            }
        }
        boardViewController.refresh();
    }
    public void saveAllNotes(){
        for (Note n : notesProperty){
            changeModelService.saveNote(n);
        }
        notificationService.notifyAllViewModels();
    }

    public Note getNote(int x) {
        try {
            return dataModel.getNote(x);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void init(ChangeModelService changeModelService, NotificationService notificationService) {
        this.changeModelService = changeModelService;
        this.notificationService = notificationService;
    }
    public void setBoardViewController(BoardViewController boardViewController) {
        this.boardViewController = boardViewController;
    }
/*

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


    public void refreshNotes() {
        loadNotes();
    }
    public void init(ChangeModelService changeModelService, NotificationService notificationService) {
        this.changeModelService = changeModelService;
        this.notificationService = notificationService;
    }

 */
}
