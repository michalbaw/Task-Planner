package app.taskplanner.view.noteview;

import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.model.notes.NoteTask;
import app.taskplanner.view.ViewController;
import app.taskplanner.viewmodel.ViewModel;
import app.taskplanner.viewmodel.noteviewmodel.NoteViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class NoteController implements ViewController {

    private ObservableList<NoteMetadata> notes;
    private NoteViewModel noteVM;
    private Note currentNote;
    private ObservableList<CheckBoxListCell<Object>> tasks;
    DataFormat task = new DataFormat("text/title", "text/toDo");

    boolean opened = false;
    @FXML
    private HBox bottomOptions;

    @FXML
    private ComboBox<NoteMetadata> changeNote;

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
    private Button addButton;

    @FXML
    private Button taskButton;


    @FXML
    private ListView<NoteTask> taskList;

    @FXML
    private TextField taskName;

    @FXML
    private SplitPane taskPane;

    @FXML
    private Label tasksDescriptions;

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
        if (!opened) {
            noteVM.checkListMode(true);
            taskPane.setMinWidth(160);
            taskPane.setPrefWidth(160);
            List<NoteTask> taskNames = noteVM.getTasks();
            taskList.getItems().addAll(taskNames);
            // TODO: 03.06.2023 how should it work?
//            taskList.setCellFactory(CheckBoxListCell.forListView(NoteTask));
            noteVM.resizeX(-160);
            opened = true;
        } else {
            noteVM.resizeX(+160);
            opened = false;
        }
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

    @FXML
    void setOnDetected(MouseEvent event) {
        Dragboard dragboard = taskList.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        int selectedId = taskList.getSelectionModel().getSelectedIndex();
        content.put(task, taskList.getItems().get(selectedId).getTaskTitle());
        dragboard.setContent(content);
        event.consume();
    }

    @FXML
    void setOnDropped(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        boolean done = false;
        if (dragboard.hasContent(task)) {
            int selectedId = taskList.getSelectionModel().getSelectedIndex();
            NoteTask noteTask = (NoteTask) dragboard.getContent(task);
            taskList.getItems().remove(noteTask);
            noteTask.setStatus(true);
            taskList.getItems().remove(noteTask);
            noteTask.setStatus(false);
            taskList.getItems().add(selectedId, noteTask);
            done = true;
        }
        event.setDropCompleted(done);
        event.consume();
    }


    @FXML
    void setOnOver(DragEvent event) {
        if (event.getGestureSource() != taskList && event.getDragboard().hasContent(task)) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    public void initialize() {
//        notes =
    }

    public void setupNote(Note currentNote) {
        this.currentNote = currentNote;
    }
}
