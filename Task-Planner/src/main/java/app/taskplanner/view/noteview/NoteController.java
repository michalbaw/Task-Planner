package app.taskplanner.view.noteview;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.Note;
import app.taskplanner.view.ViewController;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.ViewModel;
import app.taskplanner.viewmodel.noteviewmodel.NoteViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class NoteController implements ViewController {

    private NoteViewModel noteVM;
    private Note currentNote;
    @FXML
    private MenuItem closeNoSave;

    @FXML
    private Menu openAnother;

    @FXML
    private Button saveAndClose;

    @FXML
    private Label statusMessage;

    @FXML
    private TextArea noteContent;

    @FXML
    private TextField noteTitle;
    @Override
    public void init(ViewModel noteVM) {
        this.noteVM = (NoteViewModel) noteVM;
    }
    @FXML
    void closeWithoutSaving(ActionEvent event) {
    }

    @FXML
    void listOtherNotes(ActionEvent event) {
        List<Note> otherNotes = dataModel.getNotes();
        otherNotes.remove(currentNote);//tytul tej notatki

    }

    @FXML
    void saveAndClose(ActionEvent event) {
//            this.saveContent(null);
//            this.saveTitle(null);
        viewHandler.changeTitle(currentNote,noteTitle.getText());
        viewHandler.changeContent(currentNote,noteContent.getText());
//        try {
//            dataModel.saveNotes();
//        } catch (IOException e) {
//            System.out.println("saving failed");
//        }
        this.closeWithoutSaving(event);
    }

    @FXML
    void saveContent(KeyEvent event) {
        if (event != null) {

        }
    }

    @FXML
    void saveTitle(KeyEvent event) {
        if (event != null) {
            //do sth
        }
    }


    public void setupNote(Note currentNote) {
        this.currentNote = currentNote;
    }


}
