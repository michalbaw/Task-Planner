package app.taskplanner.viewmodel.noteviewmodel;

import app.taskplanner.StartApp;
import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.model.notes.NoteTask;
import app.taskplanner.viewmodel.*;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NoteViewModel implements ViewModel{
    private SingleNoteHandler singleNoteHandler;
    private DataModel dataModel;

    private ObservableList<String> tasks = FXCollections.observableArrayList();

    public ListProperty<String> taskProperty() {
        return new SimpleListProperty<>(tasks);
    }

    private Note currentNote;

    private Stage noteStage;



    void closeWithoutSaving(ActionEvent event) {
        singleNoteHandler.closeNote(currentNote);
    }

    void listOtherNotes(ActionEvent event) {
        List<NoteMetadata> otherNotes = dataModel.getNotesMetadata();
        otherNotes.remove(currentNote);//tytul tej notatki

    }

    void saveAndClose(ActionEvent event) {
        this.saveContent(null);
        this.saveTitle(null);
        //viewHandler.changeTitle(currentNote,noteTitle.getText());
        //viewHandler.changeContent(currentNote,noteContent.getText());
        try {
            dataModel.saveNote(currentNote.getMetadata().getKey());
        } catch (IOException e) {
            System.out.println("saving failed");
        }
        this.closeWithoutSaving(event);
    }

    void saveContent(KeyEvent event) {
        if (event != null) {

        }
    }

    //ToDo
    public List<NoteTask> getTasks() {
        return new ArrayList<>();
    }

    void saveTitle(KeyEvent event) {
        if (event != null) {
            //do sth
        }
    }

    public void setupNote(Note currentNote) {
        this.currentNote = currentNote;
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
}
