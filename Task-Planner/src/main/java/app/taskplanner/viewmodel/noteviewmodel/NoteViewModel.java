package app.taskplanner.viewmodel.noteviewmodel;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.SimpleTask;
import app.taskplanner.service.ChangeModelService;
import app.taskplanner.service.NotificationService;
import app.taskplanner.viewmodel.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class NoteViewModel implements ViewModel {
    private Stage noteStage;

    private DataModel dataModel;
    private SingleNoteHandler singleNoteHandler;
    private ChangeModelService changeModelService;
    private NotificationService notificationService;

    private Note currentNote;

    private final StringProperty noteContent = new SimpleStringProperty();
    private final StringProperty noteTitle = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> noteDate = new SimpleObjectProperty<>();
    private final ObservableList<SimpleTask> tasks = FXCollections.observableArrayList();


    public LocalDate getNoteDate() {
        return noteDate.get();
    }

    public void setNoteDate(LocalDate date) {
        noteDate.set(date);
    }

    public ObjectProperty<LocalDate> noteDateProperty() {
        return noteDate;
    }


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
        noteDate.setValue(currentNote.getMetadata().getDate());
        loadTasks();
    }

    public void save() {
        currentNote.getNoteBody().setContent(noteContent.get());
        currentNote.getMetadata().setTitle(noteTitle.get());
        currentNote.getMetadata().setDate(noteDate.get());
        saveTasks();

        changeModelService.saveNote(currentNote);
        notificationService.notifyViewModels(getKey());
    }

    private void saveTasks(){
        currentNote.getNoteBody().clearTasks();
        for (SimpleTask st : tasks){
            currentNote.getNoteBody().addTask(new SimpleTask(st.getTask(),st.getStatus()));
        }
    }

    private void loadTasks(){
        List<SimpleTask> loadTasks = currentNote.getNoteBody().getTasks();
        tasks.clear();
        for (SimpleTask st : loadTasks){
            SimpleTask simpleTask = st;
            tasks.add(simpleTask);
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
        noteDate.setValue(currentNote.getMetadata().getDate());
        loadTasks();

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