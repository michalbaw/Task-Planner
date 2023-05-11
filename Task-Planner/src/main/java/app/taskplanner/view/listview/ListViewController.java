package app.taskplanner.view.listview;
import app.taskplanner.model.DataModel;
import app.taskplanner.model.Note;
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
        dataModel.addNote(newTitle.getText());
        listOfNotes.refresh();
    }

    @FXML
    void deleteSelectedNotes(MouseEvent event) {
        List<String> selected = listOfNotes.getSelectionModel().getSelectedItems();
        for(String title : selected)
        {
            Note newNote = viewHandler.noteFromTitle(title);
            dataModel.removeNote(newNote);
        }
        listOfNotes.refresh();
    }
    @FXML
    void openSelectedNote(MouseEvent event) {
        List<String> selected = listOfNotes.getSelectionModel().getSelectedItems();
        for(String title : selected)
        {
         Note newNote = viewHandler.noteFromTitle(title);
         viewHandler.openNote(newNote);
        }
    }


    @Override
    public void init(ViewHandler viewHandler,DataModel dataModel){
        this.viewHandler = viewHandler;
        this.dataModel = dataModel;
        titles = (ObservableList<String>) viewHandler.listNotes();
        listOfNotes.setItems(titles);
    }

}