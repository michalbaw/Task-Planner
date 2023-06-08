package app.taskplanner.viewmodel.noteviewmodel;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.SimpleNoteBody;
import app.taskplanner.model.notes.SimpleTask;
import app.taskplanner.model.notes.Task;
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
import java.util.SortedMap;


public class NoteViewModel implements ViewModel {
    private Stage noteStage;

    private DataModel dataModel;
    private SingleNoteHandler singleNoteHandler;
    private ChangeModelService changeModelService;
    private NotificationService notificationService;

    private Note currentNote;

    private final StringProperty noteContent = new SimpleStringProperty();
    private final StringProperty noteTitle = new SimpleStringProperty();


    private final ObservableList<SimpleTask> tasks = FXCollections.observableArrayList();

    public ObservableList<SimpleTask> getTasks() {
        return tasks;
    }

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
        List<SimpleTask> loadTasks = currentNote.getNoteBody().getTasks();
        for (SimpleTask st : loadTasks){
            SimpleTask simpleTask = st;
            tasks.add(simpleTask);
        }
    }

    public void save() {
        currentNote.getNoteBody().setContent(noteContent.get());
        currentNote.getMetadata().setTitle(noteTitle.get());
        //currentNote.getNoteBody().setTasks(tasks);
        currentNote.getNoteBody().clearTasks();
        //for (SimpleTask st : tasks){
        //    currentNote.getNoteBody().addTask(new SimpleTask(st.getTask(),st.getStatus()));
        //}
        loadTasks();
        //currentNote.getNoteBody().addTask(new SimpleTask("taszczek", true));

        changeModelService.saveNote(currentNote);
        notificationService.notifyViewModels(getKey());
    }

    private void loadTasks(){
        currentNote.getNoteBody().clearTasks();
        for (SimpleTask st : tasks){
            currentNote.getNoteBody().addTask(new SimpleTask(st.getTask(),st.getStatus()));
        }
    }

    public void close() {
        notificationService.removeViewModel(this);
        singleNoteHandler.closeNote(currentNote);
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
        List<SimpleTask> loadTasks = currentNote.getNoteBody().getTasks();
        tasks.clear();
        for (SimpleTask st : loadTasks){
            SimpleTask simpleTask = st;
            tasks.add(simpleTask);
        }
    }
    public void resizeX(double X) {
        noteStage.setWidth(noteStage.getWidth() + X);
    }

    public void resizeY(double Y) {
        noteStage.setHeight(noteStage.getHeight() + Y);
    }
    public void init(SingleNoteHandler singleNoteHandler, DataModel dataModel, ChangeModelService cms, NotificationService ns, Stage noteStage) {
        this.dataModel = dataModel;
        this.noteStage = noteStage;
        this.singleNoteHandler = singleNoteHandler;
        this.changeModelService = cms;
        this.notificationService = ns;
    }
}