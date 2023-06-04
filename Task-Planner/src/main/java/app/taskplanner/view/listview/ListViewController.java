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
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class ListViewController implements ViewController {
    private ObservableList<String> titles;
    private ListViewModel listVM;

    @FXML
    private ListView<String> listOfNotes = new ListView<>();

    @FXML
    private Button newNoteButton;

    @FXML
    private TextField newTitle;

    @FXML
    private Button removeButton;

    public ListViewController() {
    }

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
        listOfNotes.setOnMouseClicked(event -> {
            if (listOfNotes.getSelectionModel().getSelectedItems() != null) {
                showOptions(listOfNotes.getSelectionModel().getSelectedIndex(), event);
            }
        });
    }

    private void showOptions(Integer item, MouseEvent event) {
        Popup popup = new Popup();
        Button openNote = new Button("Open");
        Button deleteNote = new Button("Delete");
        VBox vbox = new VBox(openNote, deleteNote);
        openNote.setOnAction(click -> {
            listVM.openNoteWithTitle(listOfNotes.getItems().get(item));
            popup.hide();
        });
        deleteNote.setOnAction(click -> {
            listVM.removeNoteAt(item);
            popup.hide();
        });
        popup.getContent().add(vbox);
        popup.show(listOfNotes.getScene().getWindow(), event.getScreenX(), event.getScreenY());
        // TODO: 03.06.2023 should disappear on dishover
        popup.hide();
    }

}