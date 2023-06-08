package app.taskplanner.view.noteview;

import app.taskplanner.model.notes.SimpleTask;
import app.taskplanner.view.ViewController;
import app.taskplanner.viewmodel.ViewModel;
import app.taskplanner.viewmodel.noteviewmodel.NoteViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static javafx.collections.FXCollections.observableArrayList;

public class NoteController implements ViewController {

    private NoteViewModel noteVM;
    boolean opened = false;

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
    @FXML
    private HBox bottomOptions;


    @FXML
    private MenuItem closeNoSave;

    @FXML
    private VBox midBox;


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
    private SplitPane taskPane;

    @FXML
    private Label tasksDescriptions;

    @FXML
    private HBox textAndTasks;



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
    void closeWithoutSaving(ActionEvent event) {

    }
    @FXML
    void listOtherNotes(ActionEvent event) {

    }
    @FXML
    void setOnDetected(MouseEvent event) {
        Dragboard dragboard = taskList.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        int selectedId = taskList.getSelectionModel().getSelectedIndex();
//        content.put(task, taskList.getItems().get(selectedId).getTaskTitle());
        dragboard.setContent(content);
        event.consume();
    }
    @FXML
    void setOnOver(DragEvent event) {
//        if (event.getGestureSource() != taskList && event.getDragboard().hasContent(task)) {
//            event.acceptTransferModes(TransferMode.MOVE);
//        }
//        event.consume();
    }
    @FXML
    void setOnDropped(DragEvent event) {
//        Dragboard dragboard = event.getDragboard();
//        boolean done = false;
//        if (dragboard.hasContent(task)) {
//            int selectedId = taskList.getSelectionModel().getSelectedIndex();
//            NoteTask noteTask = (NoteTask) dragboard.getContent(task);
//            taskList.getItems().remove(noteTask);
//            noteTask.setStatus(true);
//            taskList.getItems().remove(noteTask);
//            noteTask.setStatus(false);
//            taskList.getItems().add(selectedId, noteTask);
//            done = true;
//        }
//        event.setDropCompleted(done);
//        event.consume();
    }
    @FXML
    void openTaskPage(ActionEvent event) {
        if (!opened) {
            taskPane.setMinWidth(160);
            taskPane.setPrefWidth(160);
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
