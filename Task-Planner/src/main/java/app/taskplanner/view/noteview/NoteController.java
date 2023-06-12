package app.taskplanner.view.noteview;

import app.taskplanner.model.notes.SimpleTask;
import app.taskplanner.view.ViewController;
import app.taskplanner.view.ViewFunctions;
import app.taskplanner.viewmodel.ViewModel;
import app.taskplanner.viewmodel.noteviewmodel.NoteViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.*;

import java.time.LocalDate;


public class NoteController implements ViewController {

    private NoteViewModel noteVM;

    boolean opened = false;

    @FXML
    public MenuBar menuBar;

    @FXML
    private TextArea noteContent;

    @FXML
    private TextField noteTitle;

    @FXML
    public Button save;

    @FXML
    private ListView<SimpleTask> taskList;

    @FXML
    public TextField taskName;
    @FXML

    private SplitPane taskPane;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label statusMessage;
    private ObservableList<SimpleTask> taskItems;

    //@Override
    public void init(ViewModel noteVM) {
        this.noteVM = (NoteViewModel) noteVM;
        noteContent.textProperty().bindBidirectional(((NoteViewModel) noteVM).noteContentProperty());
        noteTitle.textProperty().bindBidirectional(((NoteViewModel) noteVM).noteTitleProperty());
        datePicker.valueProperty().bindBidirectional(((NoteViewModel) noteVM).noteDateProperty());
        taskItems = ((NoteViewModel) noteVM).getTasks();
        taskList.setItems(taskItems);
        taskList.setCellFactory(param -> new TaskCell());
        setDateColor(datePicker.getValue());
        noteContent.setWrapText(true);
        noteContent.textProperty().addListener(observable -> changeStatusMessage(true));
        noteTitle.textProperty().addListener(observable -> changeStatusMessage(true));
    }


    @FXML
    void setOnDetected(MouseEvent event) {
        Dragboard dragboard = taskList.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        dragboard.setContent(content);
        event.consume();
    }


    @FXML
    void openTaskPage() {
        if (!opened) {
            taskPane.setMinWidth(185);
            taskPane.setPrefWidth(185);
            noteVM.resizeX(-185);
            opened = true;
        } else {
            noteVM.resizeX(+185);
            opened = false;
        }
    }

    @FXML
    void saveNote() {
        setDateColor(datePicker.getValue());
        changeStatusMessage(false);
        noteVM.save();
    }

    private void setDateColor(LocalDate date) {
        this.menuBar.setStyle("-fx-background-color: " + ViewFunctions.getColorForDate(date));
    }

    @FXML
    void closeNote() {
        noteVM.close();
    }

    @FXML
    void saveAndClose() {
        saveNote();
        noteVM.close();
    }

    @FXML
    void addTask() {
        String newTaskName = taskName.getText();
        SimpleTask task = new SimpleTask(newTaskName, false);
        taskItems.add(task);
        taskName.clear();
    }

    public void resetDate() {
        datePicker.setValue(null);
        setDateColor(datePicker.getValue());
    }

    public void removeFinishedTasks() {
        taskItems.removeIf(SimpleTask::getStatus);
    }
    public void changeStatusMessage(boolean changed){
        if(changed)
        {
            statusMessage.setText("You have unsaved changes");
        }
        else{
            statusMessage.setText("Waiting for changes...");
        }
    }
}
