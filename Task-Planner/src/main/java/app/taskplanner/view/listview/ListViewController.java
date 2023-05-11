package app.taskplanner.view.listview;
import app.taskplanner.model.DataModel;
import app.taskplanner.view.ViewController;
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

public class ListViewController implements ViewController {
    private ObservableList<String> titles;
    private ViewHandler viewHandler;
    private DataModel dataModel;
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
//        titles.add(dataModel.addNote(newTitle.getText()));
    }

    @FXML
    void deleteSelectedNotes(MouseEvent event) {
//        dataModel.removeNote();
        listOfNotes.refresh();
    }
    @Override
    public void init(ViewHandler viewHandler,DataModel dataModel){
        this.viewHandler = viewHandler;
        this.dataModel = dataModel;
        listOfNotes.setItems(titles);
    }

}