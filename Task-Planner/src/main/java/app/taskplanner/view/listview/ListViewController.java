package app.taskplanner.view.listview;

import app.taskplanner.StartApp;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.view.ViewController;
import app.taskplanner.view.alerts.SelectionAlert;
import app.taskplanner.viewmodel.ViewModel;
import app.taskplanner.viewmodel.listviewmodel.ListViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ListViewController implements ViewController {
    private ListViewModel listVM;

    @FXML
    private ListView<NoteMetadata> listOfNotes = new ListView<>();

    @FXML
    private Button newNoteButton;

    @FXML
    private TextField newTitle;

    @FXML
    private Button removeButton;
    @FXML
    private Button plusButton;


    @FXML
    void createAndOpenNote(ActionEvent event) {
        listVM.addNoteWithTitle("Your new note");
        listVM.openWithKey(listOfNotes.getItems().get(listOfNotes.getItems().size()-1).getKey());
    }

    public ListViewController() {
    }

    @FXML
    void createNewNote(MouseEvent event) {
        String title = newTitle.getText();
        listVM.addNoteWithTitle(title);
        newTitle.clear();
    }

    @FXML
    void deleteSelectedNote(MouseEvent event) {
        int selectedIndex = listOfNotes.getSelectionModel().getSelectedIndex();
        if(selectedIndex != -1) {
            listVM.removeNoteAt(selectedIndex);
        }
        else {
            new SelectionAlert().show();
        }
    }

    @FXML
    void openSelectedNote(MouseEvent event) {
        NoteMetadata selectedNoteInfo = listOfNotes.getSelectionModel().getSelectedItem();
        System.out.println("selection");
        if(selectedNoteInfo != null) {
            System.out.println("openWithKey " + selectedNoteInfo.getKey());
            listVM.openWithKey(selectedNoteInfo.getKey());
        }
        else
        {
            new SelectionAlert().show();
        }
    }

    @Override
    public void init(ViewModel listVM) {
        this.listVM = (ListViewModel) listVM;
        // Bind the ListView's items property to the ViewModel's titlesProperty
        listOfNotes.itemsProperty().bindBidirectional(this.listVM.notesProperty());
        listOfNotes.setOnMouseClicked(event -> {
            if (listOfNotes.getSelectionModel().getSelectedItems() != null) {
                showOptions(listOfNotes.getSelectionModel().getSelectedIndex(), event);
            }
        });
        Image icon = new Image(Objects.requireNonNull(StartApp.class.getResourceAsStream("plus.png")));
        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(50);
        iconView.setFitWidth(50);
        plusButton.setGraphic(iconView);
        listOfNotes.setCellFactory(param -> new NoteListCell());
    }

    private void showOptions(Integer item, MouseEvent event) {
        Popup popup = new Popup();
        Button openNote = new Button("Open");
        Button deleteNote = new Button("Delete");
        VBox vbox = new VBox(openNote, deleteNote);
        Note note = listVM.getNote(listOfNotes.getItems().get(item).getKey());
        openNote.setOnAction(click -> {
            listVM.openNote(note);
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