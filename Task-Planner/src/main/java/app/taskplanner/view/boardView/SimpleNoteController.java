package app.taskplanner.view.boardView;

import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.model.notes.Task;
//import app.taskplanner.viewmodel.NoteTasks;
import app.taskplanner.viewmodel.boardviewmodel.BoardViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class SimpleNoteController {
    private boolean opened = false;
    private NoteMetadata selfNote;
    private double X = 0;
    private double Y = 0;
    private double height = 0;
    private double width = 0;
    private BoardViewModel boardVM;
    @FXML
    private Button addButton;

    @FXML
    private MenuItem closeNoSave;

    @FXML
    private TextArea noteContent;

    @FXML
    private TextField noteTitle;

    @FXML
    private Menu openAnother;

    @FXML
    private VBox pureNote;

    @FXML
    private Button taskButton;

    @FXML
    private ListView<String> taskList;

    @FXML
    private TextField taskName;

    @FXML
    private SplitPane taskPane;

    @FXML
    private Label tasksDescriptions;

    @FXML
    private HBox textAndTasks;

    SimpleNoteController(NoteMetadata noteMetadata) {
        selfNote = noteMetadata;
    }


    @FXML
    void setOnDetected(MouseEvent event) {

    }

    @FXML
    void setOnDropped(DragEvent event) {

    }

    @FXML
    void setOnOver(DragEvent event) {

    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public NoteMetadata getSelfNote() {
        return selfNote;
    }

    public void setSelfNote(NoteMetadata selfNote) {
        this.selfNote = selfNote;
    }
}

