package app.taskplanner.viewmodel.noteviewmodel;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.Note;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.ViewModel;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.util.List;

public class NoteViewModel implements ViewModel {
    private ViewHandler viewHandler;
    private DataModel dataModel;
    Note currentNote;
    @Override
    public void init(ViewHandler viewHandler, DataModel dataModel) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
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
    void saveTitle(KeyEvent event) {
        if (event != null) {
            //do sth
        }
    }
    public void setupNote(Note currentNote) {
//        this.currentNote = currentNote;
    }
}
