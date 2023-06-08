package app.taskplanner.viewmodel.noteviewmodel;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.model.notes.NoteTask;
import app.taskplanner.viewmodel.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NoteViewModel implements ViewModel {

    private Stage noteStage;

    private SingleNoteHandler singleNoteHandler;

    private DataModel dataModel;

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

    public void closeWithoutSaving() {
        singleNoteHandler.closeNote(currentNote);
    }

    void listOtherNotes(ActionEvent event) {
        List<NoteMetadata> otherNotes = dataModel.getNotesMetadata();
        otherNotes.remove(currentNote);//tytul tej notatki

    }

    public void saveAndClose() {
        this.save();
        this.closeWithoutSaving();
    }
    public void save(){
        currentNote.getNoteBody().setContent(noteContent.get());
        currentNote.getMetadata().setTitle(noteTitle.get());
        try {
            dataModel.saveNote(currentNote);
            singleNoteHandler.notifyViewModels(currentNote.getMetadata().getKey());
        } catch (IOException e) {
            System.err.println("saving failed");
        }
    }

    //ToDo
    public List<NoteTask> getTasks() {
        return new ArrayList<>();
    }


    public void setupNote(Note currentNote) {
        this.currentNote = currentNote;
        noteContent.setValue(currentNote.getNoteBody().getContent());
        noteTitle.setValue(currentNote.getMetadata().getTitle());
        System.out.println(noteContent);
        System.out.println(noteTitle);
    }

    public void checkListMode(boolean enabled) {

    }

    public void resizeX(double X) {
        noteStage.setWidth(noteStage.getWidth() + X);
    }

    public void resizeY(double Y) {
        noteStage.setHeight(noteStage.getHeight() + Y);
    }

    @Override
    public void init(Handler singleNoteHandler, DataModel dataModel) {
        this.dataModel = dataModel;
        this.singleNoteHandler = (SingleNoteHandler) singleNoteHandler;
    }

    @Override
    public void init(Handler singleNoteHandler, DataModel dataModel, Stage currentNote) {
        this.dataModel = dataModel;
        this.singleNoteHandler = (SingleNoteHandler) singleNoteHandler;
        this.noteStage = currentNote;
    }

    @Override
    public void closeWindow() {

    }
    public void refresh(){
        int key = currentNote.getMetadata().getKey();
        currentNote.setMetadata(dataModel.getMetadata(key));
        try
        {
            currentNote.setNoteBody(dataModel.getBody(key));
        }
        catch (IOException e) {
            System.out.println("Note refreshing failed");
        }
        noteTitle.setValue(currentNote.getMetadata().getTitle());
        noteContent.setValue(currentNote.getNoteBody().getContent());
    }
    public int getKey(){
        return currentNote.getMetadata().getKey();
    }
}
