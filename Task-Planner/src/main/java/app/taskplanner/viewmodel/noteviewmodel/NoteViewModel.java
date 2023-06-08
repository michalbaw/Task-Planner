package app.taskplanner.viewmodel.noteviewmodel;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.NoteTask;
import app.taskplanner.service.ChangeModelService;
import app.taskplanner.service.NotificationService;
import app.taskplanner.viewmodel.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NoteViewModel implements ViewModel {

    private Stage noteStage;
    private DataModel dataModel;
    private SingleNoteHandler singleNoteHandler;
    private ChangeModelService changeModelService;
    private NotificationService notificationService;

    private ObservableList<String> tasks = FXCollections.observableArrayList();

    public ListProperty<String> taskProperty() {
        return new SimpleListProperty<>(tasks);
    }

    private Note currentNote;

    private final StringProperty noteContent = new SimpleStringProperty();
    private final StringProperty noteTitle = new SimpleStringProperty();

    public Property<String> noteContentProperty() {
        return noteContent;
    }
    public Property<String> noteTitleProperty() {
        return noteTitle;
    }

    public void setupNote(Note currentNote) {
        this.currentNote = currentNote;
        noteContent.setValue(currentNote.getNoteBody().getContent());
        noteTitle.setValue(currentNote.getMetadata().getTitle());
        System.out.println(noteContent);
        System.out.println(noteTitle);
    }

    public void save() {
        currentNote.getNoteBody().setContent(noteContent.get());
        currentNote.getMetadata().setTitle(noteTitle.get());

        changeModelService.saveNote(currentNote);
        notificationService.notifyViewModels(getKey());
    }

    public void close() {
        notificationService.removeViewModel(this);
        singleNoteHandler.closeNote(currentNote);
    }
    public void checkListMode(boolean enabled) {

    }

    public void resizeX(double X) {
        noteStage.setWidth(noteStage.getWidth() + X);
    }

    public void resizeY(double Y) {
        noteStage.setHeight(noteStage.getHeight() + Y);
    }

    public List<NoteTask> getTasks() {
        return new ArrayList<>();
    }

    public int getKey() {
        return currentNote.getMetadata().getKey();
    }

    public void refresh() {
        int key = currentNote.getMetadata().getKey();
        currentNote.setMetadata(dataModel.getMetadata(key));
        try {
            currentNote.setNoteBody(dataModel.getBody(key));
        } catch (IOException e) {
            System.out.println("Note refreshing failed");
        }
        noteTitle.setValue(currentNote.getMetadata().getTitle());
        noteContent.setValue(currentNote.getNoteBody().getContent());
    }
    public void init(SingleNoteHandler singleNoteHandler, DataModel dataModel, ChangeModelService cms, NotificationService ns, Stage currentNote) {
        this.dataModel = dataModel;
        this.singleNoteHandler = singleNoteHandler;
        this.changeModelService = cms;
        this.notificationService = ns;
        this.noteStage = currentNote;
    }
}
