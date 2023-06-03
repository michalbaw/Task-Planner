package app.taskplanner.view.boardView;

import app.taskplanner.StartApp;
import app.taskplanner.model.notes.Note;
//import app.taskplanner.viewmodel.SimpleNote;
//import app.taskplanner.viewmodel.ViewModel;
import app.taskplanner.model.notes.SimpleNote;
import app.taskplanner.viewmodel.ViewModel;
import app.taskplanner.viewmodel.boardviewmodel.BoardViewModel;

import static javafx.application.Application.launch;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

import java.io.IOException;

public final class BoardViewController extends AnchorPane {
    private BoardViewModel boardVM;
    public static ObservableList<SimpleNote> notes;

    private double maxX; //later reassignment of notes
    private double maxY;

    public void init(ViewModel viewModel) {
        this.boardVM = (BoardViewModel) viewModel;
        refresh();
    }

    public void refresh() {
        ObservableList<SimpleNote> currentNotes;
        currentNotes = boardVM.getNotes();
            notes = currentNotes;
            clearNotes();
            setNotes();
    }

    void setNotes() {
        for (SimpleNote note : notes) {
            this.addNote();
        }
    }

    void addNote() {
        try {
            Region root;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StartApp.class.getResource("simple-note-view.fxml"));
            root = loader.load();
            root.setMinHeight(100);
            root.setMinSize(100, 100);
            this.getChildren().add(root);
        } catch (IOException ignored) {
        }
    }

    void clearNotes() {
        ObservableList<Node> nodes = this.getChildren();
        this.getChildren().clear();
    }

    public AnchorPane createView() {
        return null;
    }
}
