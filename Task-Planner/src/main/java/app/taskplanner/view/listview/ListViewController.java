package app.taskplanner.view.listview;
import app.taskplanner.view.ViewHandler;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ListViewController extends com.example.taskplanner.view.ViewController {
    private ObservableList<String> titles;
    private ViewHandler viewHandler;
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
        titles.add(viewHandler.addNote(newTitle.getText()));
    }

    @FXML
    void deleteSelectedNotes(MouseEvent event) {
        int selectedId =
        viewHandler.removeNote(listOfNotes.getSelectionModel().getSelectedItems());
        listOfNotes.refresh();
    }
    void init(ViewHandler viewHandler){
        this.viewHandler = viewHandler;
        titles = viewHandler.getNoteTitles();
        listOfNotes.setItems(titles);
    }
}