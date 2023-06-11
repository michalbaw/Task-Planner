package app.taskplanner.view.boardView;

import app.taskplanner.model.notes.NoteMetadata;
//import app.taskplanner.viewmodel.NoteTasks;
import app.taskplanner.viewmodel.boardviewmodel.BoardViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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
    private AnchorPane contentPane;

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
    private BorderPane mainBorder;
    @FXML
    private Label tasksDescriptions;

    @FXML
    private HBox textAndTasks;

    SimpleNoteController(NoteMetadata noteMetadata) {

    }
    public SimpleNoteController(){};

    public void init(NoteMetadata noteMetadata,BoardViewModel boardVM){
        this.boardVM = boardVM;
        selfNote = noteMetadata;
        noteTitle.setText(selfNote.getTitle());
        noteContent.setText(boardVM.getNote(selfNote.getKey()).getNoteBody().getContent());
        contentPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        contentPane.setBackground(new Background(new BackgroundFill(Color.LAVENDER,CornerRadii.EMPTY, Insets.EMPTY)));
        borderLine();
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
    @FXML
    void openInSeparateWindow(ActionEvent event) {

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
    public void borderLine(){
//        contentPanex.setBorder(new Border(new BorderStroke(Color.BLACK,
//                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }
    public void setSelfNote(NoteMetadata selfNote) {
        this.selfNote = selfNote;
    }
}

