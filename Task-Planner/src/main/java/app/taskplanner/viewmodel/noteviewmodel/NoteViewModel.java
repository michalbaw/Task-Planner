package app.taskplanner.viewmodel.noteviewmodel;

import app.taskplanner.StartApp;
import app.taskplanner.model.DataModel;
import app.taskplanner.model.Note;
import app.taskplanner.viewmodel.NoteTasks;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.ViewModel;
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

public class NoteViewModel implements ViewModel {
    private ViewHandler viewHandler;
    private DataModel dataModel;

    private ObservableList<String> tasks = FXCollections.observableArrayList();

    public ListProperty<String> taskProperty() {
        return new SimpleListProperty<>(tasks);
    }
    private Note currentNote;

    private Stage noteStage;

    @Override
    public void init(ViewHandler viewHandler, DataModel dataModel) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
    }

    @Override
    public void init(ViewHandler viewHandler, DataModel dataModel, Stage noteStage) {
        init(viewHandler,dataModel);
        this.noteStage = noteStage;
        Image icon = new Image(StartApp.class.getResourceAsStream("icon.png"));
        noteStage.getIcons().add(icon);
    }

    @Override
    public void closeWindow() {

    }

    void closeWithoutSaving(ActionEvent event) {
        viewHandler.closeNote(currentNote);
    }
    void listOtherNotes(ActionEvent event) {
        List<Note> otherNotes = dataModel.getNotes();
        otherNotes.remove(currentNote);//tytul tej notatki

    }
    void saveAndClose(ActionEvent event) {
            this.saveContent(null);
            this.saveTitle(null);
        //viewHandler.changeTitle(currentNote,noteTitle.getText());
        //viewHandler.changeContent(currentNote,noteContent.getText());
        try {
            dataModel.saveNotes();
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
    public List<NoteTasks> getTasks(){
        return new ArrayList<>();
    }
    void saveTitle(KeyEvent event) {
        if (event != null) {
            //do sth
        }
    }
    public void setupNote(Note currentNote) {
//        this.currentNote = currentNote;
    }
    public void checkListMode(boolean enabled){

    }
    public void resizeX(double X){
        noteStage.setWidth(noteStage.getWidth()+X);
    }
    public void resizeY(double Y){
        noteStage.setHeight(noteStage.getHeight()+Y);
    }
}
