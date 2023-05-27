package app.taskplanner.viewmodel.noteview;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.view.ViewController;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class NoteController implements ViewController {

    public Button loadChangesButton;
    private ViewHandler viewHandler;
    private DataModel dataModel;
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
    @FXML
    void closeWithoutSaving(ActionEvent event) {
        viewHandler.closeNote(currentNote);
    }

    @FXML
    void listOtherNotes(ActionEvent event) {
        List<NoteMetadata> otherNotes = dataModel.getNotesMetadata();
        otherNotes.remove(currentNote.getMetadata());//tytul tej notatki

    }

    @FXML
    void saveAndClose(ActionEvent event) {
//            this.saveContent(null);
//            this.saveTitle(null);
        viewHandler.changeTitle(currentNote.getMetadata(),noteTitle.getText());
        viewHandler.changeContent(currentNote.getMetadata(),noteContent.getText());
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


    public void init(ViewHandler viewHandler, DataModel dataModel) {
        this.viewHandler = viewHandler;
        this.dataModel = dataModel;
    }

    public void setup(Note currentNote) {
        this.currentNote = currentNote;
    }

    @Override
    public void init(ViewModel viewModel) {

    }
}
