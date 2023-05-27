package app.taskplanner.viewmodel.listview;
import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.Note;
import app.taskplanner.view.ViewController;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.ViewModel;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

public class    ListViewController implements ViewController {
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
        try {
            dataModel.addNote(newTitle.getText());
        }
        catch (IOException ioException){
            System.err.println("problem");
        }
        listOfNotes.setItems(viewHandler.listNotes());
        listOfNotes.refresh();
    }

    @FXML
    void deleteSelectedNotes(MouseEvent event) {
        List<String> selected = listOfNotes.getSelectionModel().getSelectedItems();
        for(String title : selected) {
            Note newNote = viewHandler.getNoteFromID(0);
            try {
                dataModel.removeNote(newNote.getMetadata().getKey());
            }
            catch (IOException e){
                System.err.println("fk");
            }
        }
        listOfNotes.refresh();
    }
    @FXML
    void openSelectedNote(MouseEvent event) {
        List<String> selected = listOfNotes.getSelectionModel().getSelectedItems();
        for(String title : selected)
        {
         Note newNote = viewHandler.getNoteFromID(0);
         viewHandler.openNote(newNote);
        }
    }


    public void init(ViewHandler viewHandler,DataModel dataModel){
        this.viewHandler = viewHandler;
        this.dataModel = dataModel;
        titles =  viewHandler.listNotes();
        listOfNotes.setItems(titles);
    }

    @Override
    public void init(ViewModel viewModel) {

    }
}