package app.taskplanner.view.listview;
import app.taskplanner.model.DataModel;
import app.taskplanner.model.Note;
import app.taskplanner.view.ViewController;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.ViewModel;
import app.taskplanner.viewmodel.listviewmodel.ListViewModel;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class    ListViewController implements ViewController {
    private ObservableList<String> titles;
    private ListViewModel listVM;
    @FXML
    private ListView<String> listOfNotes;
    @FXML
    private Button newNoteButton;

    @FXML
    private TextField newTitle;

    @FXML
    private Button removeButton;

    @FXML
    void createNewNote(MouseEvent event) {
    }

    @FXML
    void deleteSelectedNotes(MouseEvent event) {

    }
    @FXML
    void openSelectedNote(MouseEvent event) {
    }

    @Override
    public void init(ViewModel listVM) {
        this.listVM = (ListViewModel) listVM;
    }
}