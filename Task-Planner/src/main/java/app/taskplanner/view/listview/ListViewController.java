package app.taskplanner.view.listview;

import app.taskplanner.view.ViewController;
import app.taskplanner.viewmodel.ViewModel;
import app.taskplanner.viewmodel.listviewmodel.ListViewModel;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ListViewController implements ViewController {
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
        String title = newTitle.getText();
        listVM.addNoteWithTitle(title);
        newTitle.clear();
    }
    @FXML
    void deleteSelectedNotes(MouseEvent event) {
        int selectedIndex = listOfNotes.getSelectionModel().getSelectedIndex();
        listVM.removeNoteAt(selectedIndex);
    }

    @FXML
    void openSelectedNote(MouseEvent event) {
        String selectedTitle = listOfNotes.getSelectionModel().getSelectedItem();
        listVM.openNoteWithTitle(selectedTitle);
    }

    @Override
    public void init(ViewModel listVM) {
        this.listVM = (ListViewModel) listVM;
        // Bind the ListView's items property to the ViewModel's titlesProperty
        listOfNotes.itemsProperty().bindBidirectional(this.listVM.titlesProperty());
    }
}