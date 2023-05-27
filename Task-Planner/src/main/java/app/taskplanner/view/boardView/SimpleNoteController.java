package app.taskplanner.view.boardView;

import app.taskplanner.viewmodel.NoteTasks;
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
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class SimpleNoteController {
    private boolean opened = false;
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


    @FXML
    void openTaskPanel(ActionEvent event) {
        if (!opened) {
            System.out.println(taskPane.getLayoutX() + " " + taskPane.getLayoutY());
            boardVM.checkListMode(this,true);
            taskPane.setMinWidth(160);
            taskPane.setPrefWidth(160);
            List<NoteTasks> taskNames = boardVM.getTasks(this);
            taskList.getItems().addAll(taskNames.stream().map(NoteTasks::titleAsString).toList());
//            taskList.setCellFactory(CheckBoxListCell.forListView(NoteTasks::toDoProperty));
            System.out.println(taskPane.getLayoutX() + " " + taskPane.getLayoutY());
            boardVM.resizeX(this,160);
            opened = true;
        } else {
            boardVM.resizeX(this,-160);
            opened = false;
        }
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

}

