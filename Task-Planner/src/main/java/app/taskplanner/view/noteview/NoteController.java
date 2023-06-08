package app.taskplanner.view.noteview;

import app.taskplanner.model.notes.SimpleTask;
import app.taskplanner.view.ViewController;
import app.taskplanner.viewmodel.ViewModel;
import app.taskplanner.viewmodel.noteviewmodel.NoteViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static javafx.collections.FXCollections.observableArrayList;

public class NoteController implements ViewController {

    private NoteViewModel noteVM;

    @FXML
    public HBox noteMainContent;
    @FXML
    private TextArea noteContent;
    @FXML
    private TextField noteTitle;

    @FXML
    public HBox saveCloseButtons;
    @FXML
    public Button save;
    @FXML
    public Button close;

    @FXML
    public VBox tasks;
    @FXML
    private ListView<SimpleTask> taskList;
    @FXML
    public TextField taskName;
    @FXML
    public Button addTask;


    private ObservableList<SimpleTask> taskItems;

    @Override
    public void init(ViewModel noteVM) {
        this.noteVM = (NoteViewModel) noteVM;
        noteContent.textProperty().bindBidirectional(((NoteViewModel) noteVM).noteContentProperty());
        noteTitle.textProperty().bindBidirectional(((NoteViewModel) noteVM).noteTitleProperty());

        taskItems = ((NoteViewModel) noteVM).getTasks();
        taskList.setItems(taskItems);
        taskList.setCellFactory(param -> new TaskCell());
    }

    @FXML
    void saveNote() {
        noteVM.save();
    }

    @FXML
    void closeNote(ActionEvent event) {
        noteVM.close();
    }

    @FXML
    void saveAndClose(ActionEvent event) {
        saveNote();
        noteVM.close();
    }

    @FXML
    void addTask(ActionEvent event) {
        String newTaskName = taskName.getText();
        SimpleTask task = new SimpleTask(newTaskName, false);
        taskItems.add(task);
        taskName.clear();
    }
}
