package app.taskplanner.view.noteview;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.Note;
import app.taskplanner.view.ViewController;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.ViewModel;
import app.taskplanner.viewmodel.noteviewmodel.NoteViewModel;
import javafx.beans.binding.ObjectExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class NoteController implements ViewController {

    private NoteViewModel noteVM;
    private Note currentNote;
    private ObservableList<CheckBoxListCell<Object>> tasks;
    @FXML
    private Button addTask;

    @FXML
    private HBox bottomOptions;

    @FXML
    private ComboBox<?> changeNote;

    @FXML
    private VBox checkListVBox;

    @FXML
    private MenuItem closeNoSave;

    @FXML
    private VBox midBox;

    @FXML
    private TextArea noteContent;

    @FXML
    private TextField noteTitle;

    @FXML
    private Menu openAnother;

    @FXML
    private VBox pureNote;

    @FXML
    private VBox rightBox;

    @FXML
    private Button saveAndClose;

    @FXML
    private Label statusMessage;

    @FXML
    private Button taskButton;

    @FXML
    private ListView<CheckBoxListCell<Object>> taskList;

    @FXML
    private HBox textAndTasks;

    @Override
    public void init(ViewModel noteVM) {
        this.noteVM = (NoteViewModel) noteVM;
    }
    @FXML
    void closeWithoutSaving(ActionEvent event) {
    }

    @FXML
    void listOtherNotes(ActionEvent event) {

    }

    @FXML
    void openTaskPage(ActionEvent event) {
        noteVM.checkListMode(true);
        checkListVBox.setMinWidth(200);
//        tasks = noteVM.taskProperty();
//        taskList.itemsProperty().
    }

    @FXML
    void saveAndClose(ActionEvent event) {

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

    @FXML
    void swapNote(ActionEvent event) {
//        noteVM.setupNote();
    }
    public void setupNote(Note currentNote) {
        this.currentNote = currentNote;
    }


}
